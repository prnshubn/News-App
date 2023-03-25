package com.wipro.newsapp.top.model;

import java.util.List;

public class ListRestResponse {

    private List<TopNews> list;

    public ListRestResponse() {
    }

    public List<TopNews> getList() {
        return list;
    }

    public void setList(List<TopNews> list) {
        this.list = list;
    }

    public ListRestResponse(List<TopNews> list) {
        this.list = list;
    }
}
