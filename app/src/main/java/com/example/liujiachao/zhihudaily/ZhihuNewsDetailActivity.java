package com.example.liujiachao.zhihudaily;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by liujiachao on 2016/7/25.
 */
public class ZhihuNewsDetailActivity extends AppCompatActivity implements OnPageChangeListener{

    private TextView backMenu;
    private TextView shareMenu;
    private TextView collectMenu;
    private TextView commentMenu;
    private TextView commentNumMenu;
    private TextView praiseMenu;
    private TextView praiseNumMenu;
    ViewPager viewPager;

    private ArrayList<Integer> idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    private void initViews() {
        setContentView(R.layout.zhihu_detail_activity);
        backMenu = (TextView) findViewById(R.id.back);
        shareMenu = (TextView)findViewById(R.id.share);
        collectMenu = (TextView)findViewById(R.id.collect);
        commentMenu = (TextView)findViewById(R.id.comment);
        commentNumMenu = (TextView)findViewById(R.id.comment_num);
        praiseMenu = (TextView)findViewById(R.id.praise);
        praiseNumMenu = (TextView)findViewById(R.id.praise_num);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new NewsDetailAdapter(getSupportFragmentManager(),idList));


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
