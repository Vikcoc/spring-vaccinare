package com.vaccin.vaccin.dto;

public class ArticleListingDto {
    private final Integer id;
    private final String picture;
    private final String title;

    public ArticleListingDto(Integer id, String picture, String title) {
        this.id = id;
        this.picture = picture;
        this.title = title;
    }

    public ArticleListingDto(ArticleDto art){
        this.id = art.getId();
        this.picture = art.getPicture();
        this.title = art.getTitle();
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
}
