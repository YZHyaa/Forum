package com.xusm.forum.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created;

    private String content;

    private Integer type;

    private Long click;

    private double sort;

    private Integer commentCount;

    private Integer alike;

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Transient
    private UserDetail userDetail;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    private Integer state;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    private String images;

    public Long getId() {
        return id;
    }

    public double getSort() {
        return sort;
    }

    public void setSort(double sort) {
        this.sort = sort;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getAlike() {
        return alike;
    }

    public void setAlike(Integer alike) {
        this.alike = alike;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

}
