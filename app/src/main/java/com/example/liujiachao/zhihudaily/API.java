package com.example.liujiachao.zhihudaily;

/**
 * Created by liujiachao on 2016/7/29.
 */
public class API {
    // request type, latest stands for getting newly information
    public static final int TYPE_LATEST = 0;
    public static final int TYPE_BEFORE = 1;

    //request tag, added to cancel that request conveniently (to avoid bugs).
    public static final Object TAG_ZHIHU = "zhihu";
    public static final String TAG_SPLASH = "splash";
    //Zhihu API
    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/news/";
    public static final String NEWS_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static final String NEWS_BEFORE = "http://news-at.zhihu.com/api/4/news/before/";
    public static final String SPLASH = "http://news-at.zhihu.com/api/4/start-image/1080*1920";
}
