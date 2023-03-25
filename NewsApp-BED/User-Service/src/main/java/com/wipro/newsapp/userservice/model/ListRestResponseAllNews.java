package com.wipro.newsapp.userservice.model;

import java.util.List;

public class ListRestResponseAllNews {


    private List<News> list;

    public ListRestResponseAllNews() {
    }

    public ListRestResponseAllNews(List<News> list) {
        this.list = list;
    }

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }
}
