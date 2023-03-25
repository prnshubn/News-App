package com.wipro.newsapp.top.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table
public class TopNews {

    @Autowired
    @Embedded
    private source Source;

    private String author;

    @Id
    private String title;

    @Size(max = 1000)
    private String description;

    @Size(max = 1000)
    private String url;

    @Size(max = 1000)
    private String urlToImage;

    private String publishedAt;

    @Size(max = 1000)
    private String content;

    private boolean news;

    private String newsType;

    public TopNews() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TopNews(String author, String title, @Size(max = 1000) String description, @Size(max = 1000) String url, @Size(max = 1000) String urlToImage,
                   String publishedAt, @Size(max = 1000) String content, boolean news, String newsType) {
        super();
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.news = news;
        this.newsType=newsType;
    }

    public source getSource() {
        return Source;
    }

    public void setSource(source Source) {
        this.Source = Source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getNews() {
        return news;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public void setNews(boolean news) {
        this.news = news;
    }
}
