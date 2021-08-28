package com.example.finalprojectdiit.Model;

public class NotificationAlert {

    public int id_notification;
    public String title_notification, desc_notification, image_notification, url_notification, date_start_notification;

    public NotificationAlert() {
    }

    public NotificationAlert(int id_notification, String title_notification, String desc_notification, String image_notification,
                             String url_notification, String date_start_notification) {
        this.id_notification = id_notification;
        this.title_notification = title_notification;
        this.desc_notification = desc_notification;
        this.image_notification = image_notification;
        this.url_notification = url_notification;
        this.date_start_notification = date_start_notification;
    }

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public String getTitle_notification() {
        return title_notification;
    }

    public void setTitle_notification(String title_notification) {
        this.title_notification = title_notification;
    }

    public String getDesc_notification() {
        return desc_notification;
    }

    public void setDesc_notification(String desc_notification) {
        this.desc_notification = desc_notification;
    }

    public String getImage_notification() {
        return image_notification;
    }

    public void setImage_notification(String image_notification) {
        this.image_notification = image_notification;
    }

    public String getUrl_notification() {
        return url_notification;
    }

    public void setUrl_notification(String url_notification) {
        this.url_notification = url_notification;
    }

    public String getDate_start_notification() {
        return date_start_notification;
    }

    public void setDate_start_notification(String date_start_notification) {
        this.date_start_notification = date_start_notification;
    }
}
