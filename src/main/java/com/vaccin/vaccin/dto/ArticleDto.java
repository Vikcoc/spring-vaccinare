package com.vaccin.vaccin.dto;

public class ArticleDto {
    private final Integer id;
    private final String picture;
    private final String title;
    private final String body;

    public ArticleDto(Integer id, String picture, String title, String body) {
        this.id = id;
        this.picture = picture;
        this.title = title;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
