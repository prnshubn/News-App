package com.wipro.newsapp.userservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int editorialId;
    private String title;
    private String body;
//    private boolean isNews;

    public Editorial(int editorialId, String title, String body) {
        this.editorialId = editorialId;
        this.title = title;
        this.body = body;
    }

    public Editorial() {
    }

    public int getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(int editorialId) {
        this.editorialId = editorialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public boolean isNews() {
//        return isNews;
//    }
//
//    public void setNews(boolean news) {
//        this.isNews = news;
//    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
