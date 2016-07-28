package com.example.liujiachao.zhihudaily;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by liujiachao on 2016/7/25.
 * 操作数据库的方法都封装到该类里面去
 */
public class DB {
    public static Realm realm;

    public static <T extends RealmObject> T  getById(int id,Class<T> realmObjectClass){
        if(realm.isClosed()) {
            realm = Realm.getDefaultInstance();
        }
        return realm.where(realmObjectClass).equalTo("id",id).findFirst();
    }

    public static <T extends RealmObject> RealmResults<T> findAll(Class<T> realmObjectClass) {
        if (realm.isClosed()) {
            realm = Realm.getDefaultInstance();
        }

        return realm.where(realmObjectClass).findAll();
    }
}
