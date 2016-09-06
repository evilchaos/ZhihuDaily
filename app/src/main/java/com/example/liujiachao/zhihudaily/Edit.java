package com.example.liujiachao.zhihudaily;

import io.realm.RealmObject;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class Edit extends RealmObject {
    String bio;
    String avatar;
    String name;

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
