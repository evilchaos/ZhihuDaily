package com.example.liujiachao.zhihudaily.entity;

import com.example.liujiachao.zhihudaily.mvp.model.RealmString;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liujiachao on 2016/7/22.
 * zhihu item info about title,id,image...
 * Any JavaBean can store in realm when a class inherit RealmObject
 * JavaBean is a Serialable class
 * A serialable class contains getter/setter and constuct method
 */
public class ZhihuItemInfo extends RealmObject {

   @PrimaryKey
    private int id;
    private int type;
    private String title;
    private RealmList<RealmString> images;

    public ZhihuItemInfo() {

    }

    public  ZhihuItemInfo(int date,int type) {
        id = date;//for header we use date as id
        this.type = type;
    }

    public int getId() {
        return  id;
    }

    public int getType() {
        return type;
    }

    public String getTitle () {
        return title;
    }

    public RealmList<RealmString> getImages() {
        return images;
    }

    public void setId (int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public void setImages(RealmList<RealmString> images) {
        this.images = images;
    }
}
