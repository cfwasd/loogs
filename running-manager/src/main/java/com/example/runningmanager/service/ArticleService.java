package com.example.runningmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService extends IService<Article> {
    ResponseResult getArticleById(int id);
}
