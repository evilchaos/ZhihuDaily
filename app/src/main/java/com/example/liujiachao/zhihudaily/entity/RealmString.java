package com.example.liujiachao.zhihudaily.entity;

import io.realm.RealmObject;

/**
 * Created by liujiachao on 2016/7/22.
 */
public class RealmString extends RealmObject {

    private String val;
    public RealmString() {

    }

    public RealmString(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

}
