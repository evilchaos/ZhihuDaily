package com.example.liujiachao.zhihudaily.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liujiachao on 2016/8/10.
 */
public class NewsItem extends RealmObject {

    private boolean hasRead;
    private String date;
    @PrimaryKey
    private int id;
    private String title;
    private String image;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public boolean getHasRead() {
        return hasRead;
    }

    public String getDate() {
        return date;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}