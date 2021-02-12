package com.xusm.forum.vo;

import com.xusm.forum.constant.ReplyType;

public class ReplyRequest {

    private Integer type;

    private String content;

    private Long articleId;

    private Long commentId;

    private Long toRepAuthor;

    private String toRepAuthorName;

    public Integer getType() {
        if(type == null){
            return ReplyType.TO_COMMENT;
        }else
            return type;
    }

    public String getToRepAuthorName() {
        return toRepAuthorName;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setToRepAuthorName(String toRepAuthorName) {
        this.toRepAuthorName = toRepAuthorName;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getToRepAuthor() {
        return toRepAuthor;
    }

    public void setToRepAuthor(Long toRepAuthor) {
        this.toRepAuthor = toRepAuthor;
    }
}
