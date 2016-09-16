package com.example.liujiachao.zhihudaily.entity;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class Edit extends RealmObject implements Serializable {
    private String bio;
    private String avatar;
    private String name;


    public Edit() {
    }

    public Edit(String bio, String avatar, String name) {
        this.bio = bio;
        this.avatar = avatar;
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
