package com.example.finalprojectdiit.Model;

public class News {
    public String title, desc, image, url, date_start;

    public News() {
    }

    public News(String title, String desc, String image, String url, String date_start) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.url = url;
        this.date_start = date_start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }
}
