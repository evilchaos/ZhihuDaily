package com.example.liujiachao.zhihudaily;

import io.realm.RealmObject;

/**
 * Created by evilchaos on 16/8/20.
 */
public class StoryExtra {
    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public int getLong_comments() {
        return long_comments;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getPopularity(){
        return popularity;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getComments() {
        return comments;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public int getShort_comments() {
        return short_comments;
    }
}
