package com.example.liujiachao.zhihudaily.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.liujiachao.zhihudaily.fragment.NavigationDrawerFragment;
import com.example.liujiachao.zhihudaily.interfaces.MenuCallback;
import com.example.liujiachao.zhihudaily.entity.MyTheme;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.RecMenuAdapter;
import com.example.liujiachao.zhihudaily.entity.Theme;
import com.example.liujiachao.zhihudaily.entity.ThemeData;
import com.example.liujiachao.zhihudaily.fragment.ThemeFragment;
import com.example.liujiachao.zhihudaily.fragment.ZhihuHomeFragment;
import com.example.liujiachao.zhihudaily.listener.OnSetTitleListener;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeDataPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeDataView;
import com.example.liujiachao.zhihudaily.utils.Dater;

import java.util.ArrayList;
import java.util.List;

//访问网络，加载消息数据，并将其保存到数据库， 在该activity的生命周期中完成
public class ZhihuActivity extends AppCompatActivity implements OnSetTitleListener {

    private DrawerLayout drawerLayout;
    private boolean isTheme = false;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }


    private void initViews() {

        setContentView(R.layout.main_activity);

        setUpToolbar();

        setUpDrawer();

        setUpContentFragment();

    }

    private void setUpContentFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, new ZhihuHomeFragment());
        fragmentTransaction.commit();
    }


    private void setUpDrawer() {
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.nav_drawer_fragment);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        drawerFragment.setUpDrawer(R.id.nav_drawer_fragment, drawerLayout, toolbar);

    }

    private void setUpToolbar() {
        toolbar = (Toolbar)findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("首页");
        toolbar.inflateMenu(R.menu.toolbar_action_menu);
    }

    @Override
    public void onSetTitle(String title) {
        String date = Dater.getNewsLabel(title);
        getSupportActionBar().setTitle(date);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
//                drawerLayout.closeDrawers();
//                return true;
//            } else if (isTheme) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.content_fragment,new ZhihuHomeFragment());
//                fragmentTransaction.commit();
//                isTheme = false;
//
//                for (MyTheme myTheme : myThemeList ) {
//                    myTheme.setSelected(false);
//                }
//
//                recMenuAdapter.notifyDataSetChanged();
//            }
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

}
