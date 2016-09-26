package com.example.liujiachao.zhihudaily.entity;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by evilchaos on 16/7/29.
 * 知乎首页中的所有数据，包括广告栏要加载的数据，日期，item项要加载的所有数据
 */
public class ZhihuJson implements Serializable {
    @PrimaryKey
    private String date;

    public ZhihuJson() {
    }

    private List<ZhihuTop> top_stories;
    private List<ZhihuItemInfo> stories;

    public List<ZhihuItemInfo> getStories(){
        return stories;
    }

    public List<ZhihuTop> getTop_stories() {
        return top_stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<ZhihuItemInfo> infoList) {
        this.stories = stories;
    }

    public void setTop_stories(List<ZhihuTop> topList) {
        this.top_stories = topList;
    }


}
