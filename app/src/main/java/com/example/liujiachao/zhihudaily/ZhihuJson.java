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


    private RealmList<ZhihuTop> topList;
    private RealmList<ZhihuItemInfo> infoList;

    public RealmList<ZhihuItemInfo> getInfoList(){
        return infoList;
    }

    public RealmList<ZhihuTop> getTopList() {
        return topList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInfoList(RealmList<ZhihuItemInfo> infoList) {
        this.infoList = infoList;
    }

    public void setTopList(RealmList<ZhihuTop> topList) {
        this.topList = topList;
    }


}
