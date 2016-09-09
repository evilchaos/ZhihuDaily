package com.example.liujiachao.zhihudaily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.liujiachao.zhihudaily.entity.Edit;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by liujiachao on 2016/9/8.
 */
public class EditorDetailActivity extends AppCompatActivity {

    List<Edit> editors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        editors = (List<Edit>)intent.getSerializableExtra("edits");



    }
}
