package com.example.newsapi.Model;

public class NewsModel  {

    String title;
    String urlToImage;
    String content;
    String author;
    String publishedAt;

    public NewsModel() {
    }

    public NewsModel(String title, String urlToImage, String content, String author, String publishedAt) {
        this.title = title;
        this.urlToImage = urlToImage;
        this.content = content;
        this.author = author;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
