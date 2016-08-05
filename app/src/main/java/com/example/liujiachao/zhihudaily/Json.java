package com.example.liujiachao.zhihudaily;

import com.example.liujiachao.zhihudaily.mvp.model.RealmString;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by liujiachao on 2016/7/29.
 * 封装了Gson,解析JSON数据
 */

//public class Json {
//    public static Type token = new TypeToken<RealmList<RealmString>>(){}.getType();
//    public Json() {
//    }
//
//    public static Gson mGson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
//        @Override
//        public boolean shouldSkipField(FieldAttributes f) {
//            return f.getDeclaredClass().equals(RealmObject.class);
//        }
//
//        @Override
//        public boolean shouldSkipClass(Class<?> clazz) {
//            return false;
//        }
//    })      //用于接管某种类的序列化和反序列化过程
//            .registerTypeAdapter(token, new TypeAdapter<RealmList<RealmString>>() {
//                @Override
//                public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
//
//                }
//
//                @Override
//                public RealmList<RealmString> read(JsonReader in) throws IOException {
//                    RealmList<RealmString> list = new RealmList<>();
//                    in.beginArray();
//                    while (in.hasNext()) {
//                        list.add(new RealmString(in.nextString()));
//                    }
//                    in.endArray();
//                    return list;
//                }
//            }).create();
//
//    public static ZhihuJson parseZhihuNews(String latest) {
//        return mGson.fromJson(latest,ZhihuJson.class);
//    }
//
//    public static ZhihuDetail parseZhihuNewsDetail(String newsDetail) {
//        return mGson.fromJson(newsDetail,ZhihuDetail.class);
//    }
//}

public class Json {
    public Json() {
    }

    public static Type token = new TypeToken<RealmList<RealmString>>() {
    }.getType();


    public static Gson mGson = new GsonBuilder().
            setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .registerTypeAdapter(token, new TypeAdapter<RealmList<RealmString>>() {

                @Override
                public void write(JsonWriter out, RealmList<RealmString> value) throws io.realm.internal.IOException {
                    // Ignore
                }

                @Override
                public RealmList<RealmString> read(JsonReader in) throws io.realm.internal.IOException, java.io.IOException {
                    RealmList<RealmString> list = new RealmList<>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(new RealmString(in.nextString()));
                    }
                    in.endArray();
                    return list;
                }
            }).create();

    public static ZhihuJson parseZhihuNews(String latest) {
        return mGson.fromJson(latest, ZhihuJson.class);
    }

    public static ZhihuDetail parseZhihuNewsDetail(String detail) {
        return mGson.fromJson(detail, ZhihuDetail.class);
    }



}