package com.xusm.forum.vo;


import com.xusm.forum.entity.Comment;

import java.util.List;

public class ArticleResponse {

    private Long artId;
    private String create;
    private Long click;
    private Integer commentCount;
    private String content;
    private Integer alike;
    private String images;

    public Long getArtId() {
        return artId;
    }

    public String getCreate() {
        return create;
    }

    public Long getClick() {
        return click;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public String getContent() {
        return content;
    }

    public Integer getAlike() {
        return alike;
    }

    public String getImages() {
        return images;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorHead() {
        return authorHead;
    }

    public List<Comment> getComments() {
        return comments;
    }

    private String title;
    private String authorName;
    private String authorHead;
    private List<Comment> comments;

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setAlike(Integer alike) {
        this.alike = alike;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorHead(String authorHead) {
        this.authorHead = authorHead;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
