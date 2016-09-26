package com.example.liujiachao.zhihudaily.mvp.model;

import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.listener.OnLoadLongCommentsListener;
import com.example.liujiachao.zhihudaily.listener.OnLoadShortCommentsListener;
import com.example.liujiachao.zhihudaily.listener.OnLoadThemeContentListener;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.utils.API;
import com.example.liujiachao.zhihudaily.utils.DB;
import com.example.liujiachao.zhihudaily.utils.Json;
import com.example.liujiachao.zhihudaily.entity.NewsItem;
import com.example.liujiachao.zhihudaily.listener.OnLoadDailyThemesListener;
import com.example.liujiachao.zhihudaily.listener.OnLoadDataListener;
import com.example.liujiachao.zhihudaily.listener.OnLoadDetailListener;
import com.example.liujiachao.zhihudaily.listener.OnLoadNewsExtraListener;
import com.example.liujiachao.zhihudaily.utils.SPSave;
import com.example.liujiachao.zhihudaily.entity.StoryExtra;
import com.example.liujiachao.zhihudaily.entity.ThemeData;
import com.example.liujiachao.zhihudaily.entity.ZhihuDetail;
import com.example.liujiachao.zhihudaily.entity.ZhihuItemInfo;
import com.example.liujiachao.zhihudaily.entity.ZhihuJson;
import com.example.liujiachao.zhihudaily.entity.ZhihuTop;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.Sort;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by liujiachao on 2016/7/29.
 * mvp model层，从网络上获取数据
 */
public class ZhihuNewsModel {
    private int type;
    //连接请求时间限制,超过该时间则超时，返回错误；否则重新发起连接请求
    public static final int GET_DURATION = 2000;
    private String date;
    private long lastTime;

    public void getZhihuNews(final int type, final OnLoadDataListener listener) {
        lastTime = System.currentTimeMillis();
        this.type = type;

        //自定义CallBack
        final Callback<ZhihuJson> callback = new Callback<ZhihuJson>() {

            @Override
            public void onResponse(ZhihuJson response, int id) {
                listener.onSuccess(type, response);

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
            public ZhihuJson parseNetworkResponse(Response response, int id) throws Exception {
                //解析网络传送过来的数据
                ZhihuJson zhihuJson = Json.parseZhihuNews(response.body().string());
//                //将这些数据插入到数据库中
//                saveZhihuNews(zhihuJson);
//                date = zhihuJson.getDate();
//                if(type == API.TYPE_BEFORE) {
//                    SPSave.save("DATE",date);
//                }
                return zhihuJson;
            }
        };


        if (type == API.TYPE_LATEST) {
            //zhy封装的okhttp库--okhttputils
            OkHttpUtils.get().url(API.NEWS_LATEST).tag(API.TAG_ZHIHU).build().execute(callback);
        } else if (type == API.TYPE_BEFORE) {
            date = SPSave.get("DATE",date);
            OkHttpUtils.get().url(API.NEWS_BEFORE + date).tag(API.TAG_ZHIHU).build().execute(callback);
        }
    }

//    private void saveZhihuNews(ZhihuJson zhihuJson) {
//        if(zhihuJson != null) {
//            List<NewsItem> list = getItemList(zhihuJson);
//            DB.realm.beginTransaction();
//            //如果是新消息，那么banner栏保存的数据也要清除，因为这些数据可能已过时
//            if (type == API.TYPE_LATEST) {
//                DB.realm.where(ZhihuTop.class).findAll().clear();
//            }
//            DB.realm.copyToRealmOrUpdate(zhihuJson);
//            DB.realm.copyToRealmOrUpdate(list);
//            DB.realm.where(ZhihuJson.class).findAllSorted("date", Sort.DESCENDING);
//            DB.realm.commitTransaction();
//        }
//    }

//    private List<NewsItem> getItemList(ZhihuJson zhihuJson) {
//        List<NewsItem> list = new ArrayList<>();
//        NewsItem newsItem = new NewsItem();
//        String date = zhihuJson.getDate();
//
//        RealmList<ZhihuItemInfo> stories = zhihuJson.getStories();
//        for(ZhihuItemInfo info : stories) {
//            NewsItem tmp = new NewsItem();
//            tmp.setDate(date);
//            tmp.setTitle(info.getTitle());
//            tmp.setId(info.getId());
//            tmp.setImage(info.getImages().get(0).getVal());
//            list.add(tmp);
//
//        }
//        return list;
//    }

    public void getZhihuNewsDetail(final int news_id , final OnLoadDetailListener listener) {
        lastTime = System.currentTimeMillis();
        final Callback<ZhihuDetail> callback = new Callback<ZhihuDetail>() {
            @Override
            public ZhihuDetail parseNetworkResponse(Response response, int id) throws Exception {

                return Json.parseZhihuNewsDetail(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if(System.currentTimeMillis() - lastTime < GET_DURATION ) {
                    OkHttpUtils.get().url(API.BASE_URL + news_id).tag(API.TAG_ZHIHU).build().execute(this);
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
        OkHttpUtils.get().url(API.BASE_URL + news_id).tag(API.TAG_ZHIHU).build().execute(callback);

    }

    public void getZhihuNewsExtra(final int news_id,final OnLoadNewsExtraListener listener) {
        lastTime = System.currentTimeMillis();
        final Callback<StoryExtra> callback = new Callback<StoryExtra>() {
            @Override
            public StoryExtra parseNetworkResponse(Response response, int id) throws Exception {
                return Json.parseStoryExtra(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if(System.currentTimeMillis() - lastTime < GET_DURATION) {
                    OkHttpUtils.get().url(API.NEWS_EXTRA + news_id).tag(API.TAG_ZHIHU).build().execute(this);
                    return;
                }
                listener.OnLoadNewsExtraFailed("loading story extra failed");

            }

            @Override
            public void onResponse(StoryExtra response, int id) {
                //DB.Save(response);
                listener.OnLoadNewsExtraSuccess(response);
            }
        };
        OkHttpUtils.get().url(API.NEWS_EXTRA + news_id).tag(API.TAG_ZHIHU).build().execute(callback);

    }

    public void getLongComments(final int story_id,final OnLoadLongCommentsListener listener) {
        lastTime = System.currentTimeMillis();
        final Callback<Comments> callback = new Callback<Comments>() {
            @Override
            public Comments parseNetworkResponse(Response response, int id) throws Exception {
                return Json.parseComments(response.body().string()) ;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (System.currentTimeMillis() - lastTime < GET_DURATION) {
                    OkHttpUtils.get().url(String.format(API.STORY_LONG_COMMENTS,story_id)).tag(API.TAG_ZHIHU).build().execute(this);
                }
            }

            @Override
            public void onResponse(Comments response, int id) {
                listener.onLoadLongCommentsSuccess(response);
            }
        };
        OkHttpUtils.get().url(String.format(API.STORY_LONG_COMMENTS,story_id)).tag(API.TAG_ZHIHU).build().execute(callback);
    }

    public void getShortComments(final int story_id,final OnLoadShortCommentsListener listener) {
        lastTime = System.currentTimeMillis();
        final Callback<Comments> callback = new Callback<Comments>() {
            @Override
            public Comments parseNetworkResponse(Response response, int id) throws Exception {
                return Json.parseComments(response.body().string()) ;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (System.currentTimeMillis() - lastTime < GET_DURATION) {
                    OkHttpUtils.get().url(String.format(API.STORY_SHORT_COMMENTS,story_id)).tag(API.TAG_ZHIHU).build().execute(this);
                }
            }

            @Override
            public void onResponse(Comments response, int id) {
                listener.onLoadShortCommentsSuccess(response);
            }
        };
        OkHttpUtils.get().url(String.format(API.STORY_SHORT_COMMENTS,story_id)).tag(API.TAG_ZHIHU).build().execute(callback);
    }

    public void getZhihuDailyThemes(final OnLoadDailyThemesListener listener) {

        lastTime = System.currentTimeMillis();
        final Callback<ThemeData> callback = new Callback<ThemeData>() {
            @Override
            public void onError(Call call, Exception e, int id) {
                if(System.currentTimeMillis() - lastTime < GET_DURATION) {
                    OkHttpUtils.get().url(API.DAILY_THEMES).tag(API.TAG_ZHIHU).build().execute(this);
                    return;
                }
            }

            @Override
            public void onResponse(ThemeData response, int id) {
                listener.OnLoadDailyThemesSuccess(response);
            }

            @Override
            public ThemeData parseNetworkResponse(Response response, int id) throws Exception {
                return Json.parseZhihuDailyThemes(response.body().string());
            }
        };
        OkHttpUtils.get().url(API.DAILY_THEMES).tag(API.TAG_ZHIHU).build().execute(callback);

    }

    public void getZhihuThemeContent(final int theme_id,final OnLoadThemeContentListener listener) {
        lastTime = System.currentTimeMillis();
        final Callback<ThemeContent> callback = new Callback<ThemeContent>() {
            @Override
            public ThemeContent  parseNetworkResponse(Response response, int id) throws Exception {
                return Json.parseZhihuThemeContent(response.body().string());
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (System.currentTimeMillis() - lastTime < GET_DURATION) {
                    OkHttpUtils.get().url(API.THEME_CONTENT + theme_id).tag(API.TAG_ZHIHU).build().execute(this);
                }
            }

            @Override
            public void onResponse(ThemeContent response, int id) {
                //DB.Save(response);
                listener.onLoadThemeContentSuccess(response);
            }
        };
        OkHttpUtils.get().url(API.THEME_CONTENT + theme_id).tag(API.TAG_ZHIHU).build().execute(callback);
    }

}
