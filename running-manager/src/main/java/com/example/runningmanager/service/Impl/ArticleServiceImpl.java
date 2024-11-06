package com.example.runningmanager.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.HttpStateCode;
import com.example.common.ResponseResult;
import com.example.runningmanager.dao.entity.Article;
import com.example.runningmanager.mapper.ArticleMapper;
import com.example.runningmanager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author wzh
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public boolean saveBatch(Collection<Article> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Article> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Article> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Article entity) {
        return false;
    }

    @Override
    public Article getOne(Wrapper<Article> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Optional<Article> getOneOpt(Wrapper<Article> queryWrapper, boolean throwEx) {
        return Optional.empty();
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Article> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Article> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Article> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Article> getEntityClass() {
        return null;
    }
    @Override
    public ResponseResult getArticleById(int id) {
        List<Article> list = articleMapper.selectList(new QueryWrapper<Article>().eq("id",id));
        return new ResponseResult(HttpStateCode.OK.getCode(), "获取成功",list);
    }

    public boolean saveArticle(Article article) {
        int res = articleMapper.saveArticle(article);
        return res > 0;
    }
    public boolean updateArticle(Article article) {
        int res = articleMapper.updateArticle(article);
        return res > 0;
    }
}
