package com.wipro.newsapp.userservice.model;

import java.util.List;

public class ListRestResponse {

    private List<Editorial> list;

    public ListRestResponse() {
    }

    public ListRestResponse(List<Editorial> list) {
        this.list = list;
    }

    public List<Editorial> getList() {
        return list;
    }

    public void setList(List<Editorial> list) {
        this.list = list;
    }
}
