package com.xusm.forum.vo;

import java.util.List;

public class SourceResult<T> {

    private Integer total;

    private List<T> source;

    public SourceResult(Integer total, List<T> source) {
        this.total = total;
        this.source = source;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getSource() {
        return source;
    }

    public void setSource(List<T> source) {
        this.source = source;
    }
}
