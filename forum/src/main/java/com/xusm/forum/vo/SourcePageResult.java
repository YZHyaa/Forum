package com.xusm.forum.vo;

import java.util.List;

public class SourcePageResult<T> {

    private Integer total;

    private Integer totalPage;

    private List<T> source;

    public SourcePageResult(Integer total, Integer totalPage, List<T> source) {
        this.total = total;
        this.totalPage = totalPage;
        this.source = source;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getSource() {
        return source;
    }

    public void setSource(List<T> source) {
        this.source = source;
    }
}
