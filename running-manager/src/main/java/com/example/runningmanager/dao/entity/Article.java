package com.example.runningmanager.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.*;

import java.util.Date;

/**
 * @author wzh
 */
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // 挑战ID，主键，自增

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "picUrl")
    @TableField("picUrl")
    private String picUrl;

    @Column(name = "date")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", date=" + date +
                '}';
    }
}
