package com.example.liujiachao.zhihudaily.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.EditorAdapter;
import com.example.liujiachao.zhihudaily.entity.Edit;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by liujiachao on 2016/9/8.
 */
public class EditorDetailActivity extends AppCompatActivity {

    private List<Edit> editors;
    private RecyclerView editorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        editors = (List<Edit>)intent.getSerializableExtra("edits");

        setContentView(R.layout.editor_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.edit_toolbar);
        toolbar.setTitle("主编");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        editorListView = (RecyclerView)findViewById(R.id.rv_editor);
        EditorAdapter editorAdapter = new EditorAdapter(editors);
        editorListView.setAdapter(editorAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        editorListView.setLayoutManager(manager);
    }
}
