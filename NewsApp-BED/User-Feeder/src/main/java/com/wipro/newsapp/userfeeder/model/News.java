package com.wipro.newsapp.userfeeder.model;

public class News {


    private int newsId;

    //    private String email;
    private String title;

    private String description;

    private String content;

    private String publishedAt;
    private String sourceWebsiteName;
    private String url;
    private String urlToImage;

    public News(int newsId, String title, String description,
                String content, String publishedAt, String sourceWebsiteName, String url,
                String urlToImage) {
        super();
        this.newsId = newsId;
//        this.email = email;
        this.title = title;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
        this.sourceWebsiteName = sourceWebsiteName;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public News() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt(String publishedAt) {
        return this.publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSourceWebsiteName() {
        return sourceWebsiteName;
    }

    public void setSourceWebsiteName(String sourceWebsiteName) {
        this.sourceWebsiteName = sourceWebsiteName;
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

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

}
