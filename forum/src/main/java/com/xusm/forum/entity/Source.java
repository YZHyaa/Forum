package com.xusm.forum.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sourceId;

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

    public Date getSourceCreate() {
        return sourceCreate;
    }

    public void setSourceCreate(Date sourceCreate) {
        this.sourceCreate = sourceCreate;
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

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    private String sourceTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sourceCreate;

    private Integer sourceClick;

    private String authorName;

    private Boolean pay;

    private Long price;
}
