package org.project.controller;

import org.project.domain.ResponseResult;
import org.project.domain.dto.ArticleDTO;
import org.project.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/article")
public class ContentArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.addArticle(articleDTO);
    }
}
