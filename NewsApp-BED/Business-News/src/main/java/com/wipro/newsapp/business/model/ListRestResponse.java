package com.wipro.newsapp.business.model;

import java.util.List;

public class ListRestResponse {

    private List<BusinessNews> list;

    public ListRestResponse() {
    }

    public ListRestResponse(List<BusinessNews> list) {
        this.list = list;
    }

    public List<BusinessNews> getList() {
        return list;
    }

    public void setList(List<BusinessNews> list) {
        this.list = list;
    }
}
