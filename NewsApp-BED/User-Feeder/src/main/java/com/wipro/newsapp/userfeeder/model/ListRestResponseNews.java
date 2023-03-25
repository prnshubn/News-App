package com.wipro.newsapp.userfeeder.model;

import java.util.List;

public class ListRestResponseNews {


    private List<News> list;

    public ListRestResponseNews() {
    }

    public ListRestResponseNews(List<News> list) {
        this.list = list;
    }

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }
}
