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
    private RealmList<ZhihuTop> zhihu_top;
    private RealmList<ZhihuItemInfo> zhihu_item_info;

    public RealmList<ZhihuTop> getZhihuTop() {
        return zhihu_top;
    }

    public RealmList<ZhihuItemInfo> getZhihuItemInfo(){
        return zhihu_item_info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setZhihuTop(RealmList<ZhihuTop> zhihu_top) {
        this.zhihu_top = zhihu_top;
    }

    public void setZhihuItemInfo(RealmList<ZhihuItemInfo> zhihu_item_info) {
        this.zhihu_item_info = zhihu_item_info;
    }


}
