package com.xusm.forum.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private Long artId;

    private String comContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date comCreate;

    private Long comAuthor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArtId() {
        return artId;
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    public String getComContent() {
        return comContent;
    }

    public void setComContent(String comContent) {
        this.comContent = comContent;
    }

    public Date getComCreate() {
        return comCreate;
    }

    public void setComCreate(Date comCreate) {
        this.comCreate = comCreate;
    }

    public Long getComAuthor() {
        return comAuthor;
    }

    public void setComAuthor(Long comAuthor) {
        this.comAuthor = comAuthor;
    }

    public String getComAuthorName() {
        return comAuthorName;
    }

    public void setComAuthorName(String comAuthorName) {
        this.comAuthorName = comAuthorName;
    }


    public Integer getRepCount() {
        return repCount;
    }

    public Integer getComLike() {
        return comLike;
    }

    public void setComLike(Integer comLike) {
        this.comLike = comLike;
    }

    public void setRepCount(Integer repCount) {
        this.repCount = repCount;
    }

    private String comAuthorName;

    private Integer comLike;

    private Integer repCount;

    public Integer getComState() {
        return comState;
    }

    public void setComState(Integer comState) {
        this.comState = comState;
    }

    @JsonIgnore
    private Integer comState;

    @Transient
    private String comAuthorHead;

    public String getComAuthorHead() {
        return comAuthorHead;
    }

    public void setComAuthorHead(String comAuthorHead) {
        this.comAuthorHead = comAuthorHead;
    }
}
