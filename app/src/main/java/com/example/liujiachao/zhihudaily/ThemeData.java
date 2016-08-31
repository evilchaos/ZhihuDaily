package com.example.liujiachao.zhihudaily;

import java.util.List;

/**
 * Created by liujiachao on 2016/8/29.
 */
public class ThemeData {
    private int limit;
    private List<Theme> subscribed;
    private List<Theme> others;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Theme> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<Theme> subscribed) {
        this.subscribed = subscribed;
    }

    public List<Theme> getOthers() {
        return others;
    }

    public void setOthers(List<Theme> others) {
        this.others = others;
    }
}
