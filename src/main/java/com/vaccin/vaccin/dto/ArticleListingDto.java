package com.vaccin.vaccin.dto;

public class ArticleListingDto {
    private final Integer id;
    private final String picture;
    private final String title;
    private final int readingTime;

    public ArticleListingDto(Integer id, String picture, String title, int readingTime) {
        this.id = id;
        this.picture = picture;
        this.title = title;
        this.readingTime = readingTime;
    }

    public ArticleListingDto(ArticleDto art){
        this.id = art.getId();
        this.picture = art.getPicture();
        this.title = art.getTitle();
        this.readingTime = art.getReadingTime();
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

    public int getReadingTime() {
        return readingTime;
    }
}
