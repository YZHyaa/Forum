package com.xusm.forum.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long repId;

    @JsonIgnore
    private Long commentId;

    private String repContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date repCreate;

    private Long repAuthor;

    public String getRepAuthorHead() {
        return repAuthorHead;
    }

    public void setRepAuthorHead(String repAuthorHead) {
        this.repAuthorHead = repAuthorHead;
    }

    @Transient
    private String repAuthorHead;

    public Long getRepId() {
        return repId;
    }

    public void setRepId(Long repId) {
        this.repId = repId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getRepContent() {
        return repContent;
    }

    public void setRepContent(String repContent) {
        this.repContent = repContent;
    }

    public Date getRepCreate() {
        return repCreate;
    }

    public void setRepCreate(Date repCreate) {
        this.repCreate = repCreate;
    }

    public Long getRepAuthor() {
        return repAuthor;
    }

    public void setRepAuthor(Long repAuthor) {
        this.repAuthor = repAuthor;
    }

    public String getRepAuthorName() {
        return repAuthorName;
    }

    public void setRepAuthorName(String repAuthorName) {
        this.repAuthorName = repAuthorName;
    }

    public Integer getRepLike() {
        return repLike;
    }

    public void setRepLike(Integer repLike) {
        this.repLike = repLike;
    }

    private String repAuthorName;


    private Integer repLike;

    private Integer repType;

    public Integer getRepType() {
        return repType;
    }

    public void setRepType(Integer repType) {
        this.repType = repType;
    }

    public String getToRepAuthorName() {
        return toRepAuthorName;
    }

    public void setToRepAuthorName(String toRepAuthorName) {
        this.toRepAuthorName = toRepAuthorName;
    }

    private String toRepAuthorName;

    public Long getToRepAuthor() {
        return toRepAuthor;
    }

    public void setToRepAuthor(Long toRepAuthor) {
        this.toRepAuthor = toRepAuthor;
    }

    private Long toRepAuthor;

    @JsonIgnore
    private Integer repState;

    public Integer getRepState() {
        return repState;
    }

    public void setRepState(Integer repState) {
        this.repState = repState;
    }
}
