package com.example.liujiachao.zhihudaily;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class ZhihuActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ZhihuListAdapter zhihuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

    }

    private void initViews() {
        Context context = getBaseContext();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        zhihuListAdapter = new ZhihuListAdapter();
        recyclerView.setAdapter(zhihuListAdapter);



    }
}
