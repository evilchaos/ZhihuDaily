package com.example.liujiachao.zhihudaily.entity;

import io.realm.RealmObject;

/**
 * Created by evilchaos on 16/8/21.
 */
public class Recommender extends RealmObject {
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
