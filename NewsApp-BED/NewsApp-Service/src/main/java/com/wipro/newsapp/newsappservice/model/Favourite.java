package com.wipro.newsapp.newsappservice.model;

public class Favourite {
    private Integer id;

    private String email;
    private String title;
    private String contentType;

    private String isNews;

    public Favourite() {
    }

    public Favourite(Integer id, String email, String title, String contentType, String isNews) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.contentType = contentType;
        this.isNews = isNews;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getIsNews() {
        return isNews;
    }

    public void setIsNews(String isNews) {
        this.isNews = isNews;
    }
}
