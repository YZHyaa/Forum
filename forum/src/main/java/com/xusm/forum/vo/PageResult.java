package com.xusm.forum.vo;

import java.util.List;
import java.util.Map;

public class PageResult {
    private Integer totalPage;

    private List<ArticleListResponse> articles;

    private Map<String,Object> info;


    public Map<String, Object> getinfo() {
        return info;
    }

    public PageResult(Integer totalPage, List<ArticleListResponse> articles, Map<String, Object> info) {
        this.totalPage = totalPage;
        this.articles = articles;
        this.info = info;
    }

    public void setinfo(Map<String, Object> info) {
        this.info = info;
    }

    public PageResult() {
    }

    public List<ArticleListResponse> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleListResponse> articles) {
        this.articles = articles;
    }


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


}
