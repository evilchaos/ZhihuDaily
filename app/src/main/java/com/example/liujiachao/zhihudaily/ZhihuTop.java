package com.example.liujiachao.zhihudaily;

import io.realm.RealmObject;

/**
 * Created by liujiachao on 2016/7/21.
 * zhihu banner info about image,id ,title
 */
public class ZhihuTop extends RealmObject {
    private String image;
    private String title;
    private  int id;

    public void setImage(String image) {
        this.image = image ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public  int getId() {
        return id;
    }

    public  String getTitle() {
        return title;
    }
}
