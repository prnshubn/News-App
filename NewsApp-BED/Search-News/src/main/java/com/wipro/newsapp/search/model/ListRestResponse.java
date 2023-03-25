package com.wipro.newsapp.search.model;

import java.util.List;

public class ListRestResponse {

    private List<SearchNews> list;

    public ListRestResponse() {
    }

    public ListRestResponse(List<SearchNews> list) {
        this.list = list;
    }

    public List<SearchNews> getList() {
        return list;
    }

    public void setList(List<SearchNews> list) {
        this.list = list;
    }
}
