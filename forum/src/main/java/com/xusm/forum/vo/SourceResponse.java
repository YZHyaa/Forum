package com.xusm.forum.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SourceResponse {

    private Integer sourceId;

    private String sourceTitle;

    private Integer sourceClick;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public Integer getSourceClick() {
        return sourceClick;
    }

    public void setSourceClick(Integer sourceClick) {
        this.sourceClick = sourceClick;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getSourceCreate() {
        return sourceCreate;
    }

    public void setSourceCreate(Date sourceCreate) {
        this.sourceCreate = sourceCreate;
    }

    private String authorName;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date sourceCreate;
}
