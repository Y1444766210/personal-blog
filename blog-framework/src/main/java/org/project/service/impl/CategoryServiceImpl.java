package org.project.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.constants.SystemConstants;
import org.project.domain.ResponseResult;
import org.project.domain.bo.CategoryExcel;
import org.project.domain.entity.Article;
import org.project.domain.entity.Category;
import org.project.domain.vo.CategoryListVO;
import org.project.domain.vo.CategoryVo;
import org.project.enums.AppHttpCodeEnum;
import org.project.mapper.CategoryMapper;
import org.project.service.ArticleService;
import org.project.service.CategoryService;
import org.project.utils.BeanCopyUtils;
import org.project.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-04-14 17:56:19
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private HttpServletResponse response;


    @Override
    public ResponseResult getCategoryList() {
        //查询文章表  状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().
                filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = null;
        try {
            categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public ResponseResult listAllCategory() {
        List<Category> categorylist = list();
        List<CategoryListVO> resultlist = categorylist.stream()
                .map(category -> BeanCopyUtils.copyBean(category, CategoryListVO.class))
                .collect(Collectors.toList());
        return ResponseResult.okResult(resultlist);
    }

    @Override
    public ResponseResult exportCategory() {
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = this.list();

            List<CategoryExcel> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, CategoryExcel.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), CategoryExcel.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
        return ResponseResult.okResult();
    }
}
