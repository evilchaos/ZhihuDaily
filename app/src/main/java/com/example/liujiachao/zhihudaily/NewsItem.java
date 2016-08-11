package com.example.liujiachao.zhihudaily;

import io.realm.RealmObject;

/**
 * Created by liujiachao on 2016/8/10.
 */
public class NewsItem extends RealmObject {
    private int type;
    private boolean hasRead;
    private String date;
    private ZhihuItemInfo itemInfo;

    public int getType() {
        return type;
    }

    public boolean getHasRead() {
        return hasRead;
    }

    public String getDate() {
        return date;
    }

    public ZhihuItemInfo getItemInfo() {
        return itemInfo;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItemInfo(ZhihuItemInfo itemInfo) {
        this.itemInfo = itemInfo;
    }

}
