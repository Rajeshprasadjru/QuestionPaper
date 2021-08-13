package com.questionpaper.Model;

public class Book {

    String name, url,date,bid;

    public Book() {
    }

    public Book(String name, String url, String date, String bid) {
        this.name = name;
        this.url = url;
        this.date = date;
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
