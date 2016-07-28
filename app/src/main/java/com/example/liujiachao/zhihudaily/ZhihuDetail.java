package com.example.liujiachao.zhihudaily;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by evilchaos on 16/7/29.
 * 每一个知乎消息的详细内容
 */
public class ZhihuDetail extends RealmObject{
    private  String title;
    private String body;
    private String image;
    private String url;

    @PrimaryKey
    private  int id;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  String getTitle() {
        return  title;
    }

    public String getBody() {
        return  body;
    }

    public  String getImage() {
        return  image;
    }

    public  String getUrl() {
        return url;
    }

}
