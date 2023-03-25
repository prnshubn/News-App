package com.wipro.newsapp.newsappservice.model;

public class FavNews {
    private String email;

    private String title;
    private String contentType;

    public FavNews(String email, String title, String contentType) {
        this.email = email;
        this.title = title;
        this.contentType = contentType;
    }

    public FavNews() {
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
}
