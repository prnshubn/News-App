package com.wipro.newsapp.userfeeder.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int editorialId;
    private String title;
    private String body;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
