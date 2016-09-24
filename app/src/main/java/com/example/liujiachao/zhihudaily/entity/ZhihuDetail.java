package com.example.liujiachao.zhihudaily.entity;

import io.realm.RealmList;
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
    private String share_url;
    private RealmList<Recommender> recommenders;
    private RealmList<RealmString> css;
    private RealmList<RealmString> js;
    private String image_source;



    @PrimaryKey
    private  int id;

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setImage(String image) {
        this.image = image;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public RealmList<Recommender> getRecommenders() {
        return recommenders;
    }

    public void setRecommenders(RealmList<Recommender> recommenders) {
        this.recommenders = recommenders;
    }

    public RealmList<RealmString> getCss() {
        return css;
    }

    public void setCss(RealmList<RealmString> css) {
        this.css = css;
    }

    public RealmList<RealmString> getJs() {
        return js;
    }

    public void setJs(RealmList<RealmString> js) {
        this.js = js;
    }
}
