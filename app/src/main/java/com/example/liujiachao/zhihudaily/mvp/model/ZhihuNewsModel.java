package com.example.liujiachao.zhihudaily.mvp.model;

import android.util.Log;

import com.example.liujiachao.zhihudaily.API;
import com.example.liujiachao.zhihudaily.DB;
import com.example.liujiachao.zhihudaily.Json;
import com.example.liujiachao.zhihudaily.NewsItem;
import com.example.liujiachao.zhihudaily.OnLoadDataListener;
import com.example.liujiachao.zhihudaily.OnLoadDetailListener;
import com.example.liujiachao.zhihudaily.ZhihuDetail;
import com.example.liujiachao.zhihudaily.ZhihuItemInfo;
import com.example.liujiachao.zhihudaily.ZhihuJson;
import com.example.liujiachao.zhihudaily.ZhihuListAdapter;
import com.example.liujiachao.zhihudaily.ZhihuTop;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.Sort;
import okhttp3.Call;
import okhttp3.Response;

import static com.example.liujiachao.zhihudaily.DB.saveList;

/**
 * Created by liujiachao on 2016/7/29.
 * mvp model层，从网络上获取数据
 */
public class ZhihuNewsModel {
    private int type;
    //连接请求时间限制,超过该时间则超时，返回错误；否则重新发起连接请求
    public static final int GET_DURATION = 2000;

    private long lastTime;

    public void getZhihuNews(final int type, final OnLoadDataListener listener) {
        lastTime = System.currentTimeMillis();
        this.type = type;

        //自定义CallBack
        final Callback<String> callback = new Callback<String>() {

            @Override
            public void onResponse(String response, int id) {
                listener.onSuccess();

            }

            @Override
            public void onError(Call call, Exception e, int id) {
                //返回数据失败，重新发起网络请求

               if(System.currentTimeMillis() - lastTime < GET_DURATION) {

                   if (type == API.TYPE_LATEST) {
                       //zhy封装的okhttp库--okhttputils
                       OkHttpUtils.get().url(API.NEWS_LATEST).tag(API.TAG_ZHIHU).build().execute(this);
                   } else if (type == API.TYPE_BEFORE) {
                       OkHttpUtils.get().url(API.NEWS_BEFORE).tag(API.TAG_ZHIHU).build().execute(this);
                   }
               }
                listener.onFailure("loading zhihu news failed");

            }

            @Override
            public String parseNetworkResponse(Response response, int id) throws Exception {
                //解析网络传送过来的数据
                ZhihuJson zhihuJson = Json.parseZhihuNews(response.body().string());
                //将这些数据插入到数据库中
                saveZhihuNews(zhihuJson);

                return response.body().string();
            }
        };


        if (type == API.TYPE_LATEST) {
            //zhy封装的okhttp库--okhttputils
            OkHttpUtils.get().url(API.NEWS_LATEST).tag(API.TAG_ZHIHU).build().execute(callback);
        } else if (type == API.TYPE_BEFORE) {
            OkHttpUtils.get().url(API.NEWS_BEFORE).tag(API.TAG_ZHIHU).build().execute(callback);
        }
    }

    private void saveZhihuNews(ZhihuJson zhihuJson) {
        if(zhihuJson != null) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            //如果是新消息，那么banner栏保存的数据也要清除，因为这些数据可能已过时
            if (type == API.TYPE_LATEST) {
                realm.where(ZhihuTop.class).findAll().clear();
            }
            realm.copyToRealmOrUpdate(zhihuJson);
            realm.where(ZhihuJson.class).findAllSorted("date", Sort.DESCENDING);
            realm.commitTransaction();

            List<NewsItem> list = getItemList(zhihuJson);
            DB.saveList(list);
        }
    }

    private List<NewsItem> getItemList(ZhihuJson zhihuJson) {
        List<NewsItem> list = new ArrayList<>();
        NewsItem newsItem = new NewsItem();
        String date = zhihuJson.getDate();
        newsItem.setType(ZhihuListAdapter.TYPE_DATE);
        newsItem.setDate(date);
        list.add(newsItem);

        RealmList<ZhihuItemInfo> stories = zhihuJson.getStories();
        for(ZhihuItemInfo info : stories) {
            NewsItem tmp = new NewsItem();
            tmp.setDate(date);
            tmp.setType(ZhihuListAdapter.TYPE_ITEM);
            tmp.setItemInfo(info);
            list.add(tmp);
        }
        return list;
    }

    public void getZhihuNewsDetail(final ZhihuItemInfo zhihuItemInfo, final OnLoadDetailListener listener) {
        final Callback<ZhihuDetail> callback = new Callback<ZhihuDetail>() {
            @Override
            public ZhihuDetail parseNetworkResponse(Response response, int id) throws Exception {

                return Json.parseZhihuNewsDetail(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if(System.currentTimeMillis() - lastTime < GET_DURATION ) {
                    OkHttpUtils.get().url(API.BASE_URL + zhihuItemInfo.getId()).tag(API.TAG_ZHIHU).build().execute(this);
                    return;
                }
                listener.onLoadDetailFailed("loading zhihu detail failed");

            }

            @Override
            public void onResponse(ZhihuDetail response, int id) {
                DB.Save(response);
                listener.onGetDetailSuccess(response);

            }
        };
        OkHttpUtils.get().url(API.BASE_URL + zhihuItemInfo.getId()).tag(API.TAG_ZHIHU).build().execute(callback);

    }
}
