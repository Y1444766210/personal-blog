package org.project.controller;

import org.project.domain.ResponseResult;
import org.project.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result = articleService.hostArticleList();
        return result;
    }

    @GetMapping("/articleList")
    public ResponseResult ArticleList(Integer pageNum, Integer pageSize, Long categoryId){
        ResponseResult result = articleService.ArticleList(pageNum, pageSize, categoryId);
        return result;
    }

    @GetMapping("/{id}")
    public ResponseResult ArticleDetail(@PathVariable("id") Long id){
        ResponseResult result = articleService.ArticleDetail(id);
        return result;
    }

}
