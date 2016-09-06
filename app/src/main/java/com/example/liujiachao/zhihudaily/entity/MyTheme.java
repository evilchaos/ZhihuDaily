package com.example.liujiachao.zhihudaily.entity;

import com.example.liujiachao.zhihudaily.entity.Theme;

/**
 * Created by liujiachao on 2016/8/31.
 */
public class MyTheme {

    private int serial_num;
    private boolean selected;
    private boolean subscribed;
    private Theme theme;

    public int getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(int serial_num) {
        this.serial_num = serial_num;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
