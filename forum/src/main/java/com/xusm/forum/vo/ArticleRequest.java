package com.xusm.forum.vo;

/**
 * 保存文章的请求对象
 */
public class ArticleRequest {

    private Integer type;

    private String title;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    private String content;

    private String images;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
