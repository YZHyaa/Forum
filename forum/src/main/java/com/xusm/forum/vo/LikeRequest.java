package com.xusm.forum.vo;

import com.xusm.forum.constant.LikeType;

public class LikeRequest {

    private Integer type;

    private Long toId;

    public Integer getType() {
        if(type == null){
            return LikeType.ARTICLE_LIKE;
        }
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }
}
