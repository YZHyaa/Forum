package com.xusm.forum.vo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RankListResponse {

    @JsonProperty(value = "articleId")
    private Long id;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
