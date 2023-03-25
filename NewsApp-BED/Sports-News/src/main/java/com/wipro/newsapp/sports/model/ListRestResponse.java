package com.wipro.newsapp.sports.model;

import java.util.List;

public class ListRestResponse {

    private List<SportsNews> list;

    public ListRestResponse() {
    }

    public ListRestResponse(List<SportsNews> list) {
        this.list = list;
    }

    public List<SportsNews> getList() {
        return list;
    }

    public void setList(List<SportsNews> list) {
        this.list = list;
    }
}
