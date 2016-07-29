package com.example.liujiachao.zhihudaily.mvp.model;

import com.example.liujiachao.zhihudaily.API;
import com.example.liujiachao.zhihudaily.Json;
import com.example.liujiachao.zhihudaily.OnLoadDataListener;
import com.example.liujiachao.zhihudaily.ZhihuJson;
import com.example.liujiachao.zhihudaily.ZhihuTop;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import io.realm.Realm;
import io.realm.Sort;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by liujiachao on 2016/7/29.
 */
public class ZhihuNewsModel {
    private int type;

    public void getNews(final int type, final OnLoadDataListener listener) {
        this.type = type;

        //自定义CallBack
        final Callback<String> callback = new Callback<String>() {

            @Override
            public void onResponse(String response, int id) {

            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public String parseNetworkResponse(Response response, int id) throws Exception {
                //解析网络传送过来的数据
                ZhihuJson zhihuJson = Json.parseZhihuNews(response.body().toString());
                //将这些数据插入到数据库中
                saveZhihuNews(zhihuJson);

                return null;
            }
        };

        if (type == API.TYPE_LATEST) {
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
        }
    }
}
