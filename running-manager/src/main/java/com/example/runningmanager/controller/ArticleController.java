package com.example.runningmanager.controller;

import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.Article;
import com.example.runningmanager.service.ArticleService;
import com.example.runningmanager.service.Impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author wzh
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/article/{id}")
    public ResponseResult getArticleById(@PathVariable("id") int id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/article/add")
    public ResponseResult addArticle(@RequestBody Article article) {
        article.setDate(new Date());
        System.out.println(article);
        boolean result = articleService.saveArticle(article);
        if (result) {
            return new ResponseResult(200, "添加成功", null);
        }
        return new ResponseResult(500, "添加失败", null);
    }

    @PostMapping("/article/update")
    public ResponseResult updateArticle(@RequestBody Article article) {
        article.setDate(new Date());
        boolean result = articleService.updateArticle(article);
        if (result) {
            return new ResponseResult(200, "更新成功", null);
        }
        return new ResponseResult(500, "更新失败", null);
    }
}
