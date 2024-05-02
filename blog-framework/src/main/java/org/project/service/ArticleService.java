package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.ResponseResult;
import org.project.domain.entity.Article;


public interface ArticleService extends IService<Article> {
    ResponseResult hostArticleList();

    ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult ArticleDetail(Long id);
}
