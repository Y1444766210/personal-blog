package org.project.service.impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataSizeUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.domain.ResponseResult;
import org.project.domain.dto.ArticleDTO;
import org.project.domain.entity.Article;
import org.project.domain.entity.Category;
import org.project.domain.entity.Tag;
import org.project.domain.vo.*;
import org.project.mapper.ArticleMapper;
import org.project.service.ArticleService;
import org.project.service.CategoryService;
import org.project.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.project.utils.BeanCopyUtils;

import java.util.List;
import java.util.Objects;

import static org.project.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult hostArticleList() {
        //查询热门文章，封装成resultmap返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<Article>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询十条
        Page<Article> pages = new Page(1, 10);
        page(pages, queryWrapper);
        List<Article> articles = pages.getRecords();
        List<HotArticleVo> articleVos;
        try {
            articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //如果有categoryId就要和传入的相同
        queryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0, Article::getCategoryId, categoryId);
        //状态是正式发布的
        queryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        //对isTop进行降序排序
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, queryWrapper);
        List<Article> articles = page.getRecords();
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        List<ArticleListVo> articleListVos;
        try {
            articleListVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult ArticleDetail(Long id) {
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(Long.valueOf(viewCount));
        //将Article转化为Vo形式
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Category category = categoryService.getById(article.getCategoryId());
        if(category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //将对应浏览量+1
        redisCache.incrementCacheMapValue("article:viewCount", id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult addArticle(ArticleDTO articleDTO) {
        Article article = BeanCopyUtils.copyBean(articleDTO, Article.class);
        article.setCreateTime(DateTime.now());
        save(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(title != null, Article::getTitle, title);
        queryWrapper.eq(summary != null, Article::getSummary, summary);
        Page<Article> page = new Page<>(pageNum, pageSize);
        List<Article> articles = page(page, queryWrapper).getRecords();
        List<ArticleListAdminVO> result;
        try {
            result = BeanCopyUtils.copyBeanList(articles, ArticleListAdminVO.class);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        PageVo pageVo = new PageVo(result, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticle(Long id) {
        Article article = getById(id);
        ArticleEditVO articleEditVO = BeanCopyUtils.copyBean(article, ArticleEditVO.class);
        List<String> tags = this.getBaseMapper().selectTagByArticleId(id);
        articleEditVO.setTags(tags);
        return ResponseResult.okResult(articleEditVO);
    }
}
