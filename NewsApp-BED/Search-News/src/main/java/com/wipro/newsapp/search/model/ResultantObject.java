package com.wipro.newsapp.search.model;

public class ResultantObject {

    private String status;
    private double totalResults;
    private SearchNews[] articles;

    public ResultantObject() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResultantObject(String status, double totalResults, SearchNews[] articles) {
        super();
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(double totalResults) {
        this.totalResults = totalResults;
    }

    public SearchNews[] getArticles() {
        return articles;
    }

    public void setArticles(SearchNews[] articles) {
        this.articles = articles;
    }


}
