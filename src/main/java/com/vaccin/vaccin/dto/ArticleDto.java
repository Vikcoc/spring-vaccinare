package com.vaccin.vaccin.dto;

public class ArticleDto {
    private final Integer id;
    private final String picture;
    private final String title;
    private final String body;
    private final int readingTime;

    public ArticleDto(Integer id, String picture, String title, String body, int readingTime) {
        this.id = id;
        this.picture = picture;
        this.title = title;
        this.body = body;
        this.readingTime = readingTime;
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

    public int getReadingTime() {
        return readingTime;
    }
}
