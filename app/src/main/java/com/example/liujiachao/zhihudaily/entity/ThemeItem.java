package com.example.liujiachao.zhihudaily.entity;

import com.example.liujiachao.zhihudaily.mvp.model.RealmString;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeItem extends RealmObject{
    @PrimaryKey
    private int id;
    private RealmList<RealmString> images;
    private int type;
    private String title;

    public ThemeItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<RealmString> getImages() {
        return images;
    }

    public void setImages(RealmList<RealmString> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
