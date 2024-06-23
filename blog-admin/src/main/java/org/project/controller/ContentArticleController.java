package org.project.controller;

import org.project.domain.ResponseResult;
import org.project.domain.dto.ArticleAdminDTO;
import org.project.domain.dto.ArticleDTO;
import org.project.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ContentArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult addArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.addArticle(articleDTO);
    }

    @GetMapping("/list")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.getArticleList(pageNum, pageSize, title, summary);
    }

    /**
     * 查询文章详情
     *
     * @param id 文章id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @PutMapping
    public ResponseResult updateArticle(@RequestBody ArticleAdminDTO articleAdminDTO) {
        return articleService.updateArticle(articleAdminDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }
}
