package com.wipro.newsapp.userfeeder.model;

import java.util.List;

public class ListRestResponseEdi {

    private List<Editorial> list;

    public ListRestResponseEdi() {
    }

    public ListRestResponseEdi(List<Editorial> list) {
        this.list = list;
    }

    public List<Editorial> getList() {
        return list;
    }

    public void setList(List<Editorial> list) {
        this.list = list;
    }
}
