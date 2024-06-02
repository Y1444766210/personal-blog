package org.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.project.domain.ResponseResult;
import org.project.domain.dto.ArticleDTO;
import org.project.domain.entity.Article;


public interface ArticleService extends IService<Article> {
    ResponseResult hostArticleList();

    ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult ArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(ArticleDTO articleDTO);

    ResponseResult getArticleList(Integer pageNum, Integer pageSize, String title, String summary);
}
