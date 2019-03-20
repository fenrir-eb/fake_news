package com.example.jmarkovic16_homework1.model;

public class Article {

    private int id;
    private String title;
    private String imgUrl;

    public Article(int id,String title){
        this.id = id;
        this.title = title;
        this.imgUrl = "https://picsum.photos/300/300/?random";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
