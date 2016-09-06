package com.example.liujiachao.zhihudaily;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by evilchaos on 16/7/29.
 * 知乎首页中的所有数据，包括广告栏要加载的数据，日期，item项要加载的所有数据
 */
public class ZhihuJson extends RealmObject {
    @PrimaryKey
    private String date;

    public ZhihuJson() {
    }

    private RealmList<ZhihuTop> top_stories;
    private RealmList<ZhihuItemInfo> stories;

    public RealmList<ZhihuItemInfo> getStories(){
        return stories;
    }

    public RealmList<ZhihuTop> getTop_stories() {
        return top_stories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(RealmList<ZhihuItemInfo> infoList) {
        this.stories = stories;
    }

    public void setTop_stories(RealmList<ZhihuTop> topList) {
        this.top_stories = topList;
    }


}
