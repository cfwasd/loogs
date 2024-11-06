package com.example.runningmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.runningmanager.dao.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author wzh
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Insert("insert into article(title, content, picUrl, date) " +
            "values (#{title}, #{content}, #{picUrl}, #{date})")
    int saveArticle(Article article);

    @Update("update article set title = #{title}," +
            " content = #{content}," +
            " picUrl = #{picUrl}," +
            " date = #{date}" +
            " where id = #{id}")
    int updateArticle(Article article);


}
