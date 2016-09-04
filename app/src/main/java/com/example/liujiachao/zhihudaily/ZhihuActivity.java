package com.example.liujiachao.zhihudaily;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;


import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeDataPresenter;
import com.example.liujiachao.zhihudaily.mvp.presenter.ZhihuNewsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeDataView;
import com.example.liujiachao.zhihudaily.mvp.view.ZhihuNewsView;
import com.example.liujiachao.zhihudaily.utils.DB;

import java.util.ArrayList;
import java.util.List;

//访问网络，加载消息数据，并将其保存到数据库， 在该activity的生命周期中完成
public class ZhihuActivity extends AppCompatActivity implements ThemeDataView {

    private ThemeDataPresenter  themeDataPresenter;


    private RecyclerView swipe_rec_menu;
    private DrawerLayout drawerLayout;
    private boolean isTheme;


    private Toolbar toolbar;
    private Context context;

    private ThemeData themeData;
    private List<MyTheme> myThemeList;
    private RecMenuAdapter recMenuAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }


    private void initViews() {

        themeDataPresenter = new ThemeDataPresenter(this);
        themeDataPresenter.loadThemeData();

        context = getBaseContext();
        setContentView(R.layout.navi_fragment);
        toolbar =(Toolbar)findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment,new ZhihuHomeFragment());
        fragmentTransaction.commit();

        getSupportActionBar().setTitle("首页");

        //左上角图标可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        swipe_rec_menu = (RecyclerView)findViewById(R.id.swipe_rec_menu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);


        recMenuAdapter = new RecMenuAdapter(context,myThemeList);

        swipe_rec_menu.setAdapter(recMenuAdapter);

    }



    @Override

    public void PassThemeDataToActivity(ThemeData themeData) {

        int serial_num = 0;
        List<MyTheme> myThemeList = new ArrayList<>();
        this.themeData = themeData;
        for (Theme item : themeData.getSubscribed() ) {
            MyTheme myTheme = new MyTheme();
            myTheme.setTheme(item);
            myTheme.setSerial_num(serial_num);
            myTheme.setSubscribed(true);
            myTheme.setSelected(false);
            myThemeList.add(myTheme);
            serial_num++;
        }

        for (Theme item : themeData.getOthers()) {
            MyTheme myTheme = new MyTheme();
            myTheme.setTheme(item);
            myTheme.setSerial_num(serial_num);
            myTheme.setSubscribed(false);
            myTheme.setSelected(false);
            myThemeList.add(myTheme);
            serial_num++;
        }

        this.myThemeList = myThemeList;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_action_menu, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawers();
                return true;
            } else if (isTheme) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_fragment,new ZhihuHomeFragment());
                fragmentTransaction.commit();
                isTheme = false;

                for (MyTheme myTheme : myThemeList ) {
                    myTheme.setSelected(false);
                }

                recMenuAdapter.notifyDataSetChanged();
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
