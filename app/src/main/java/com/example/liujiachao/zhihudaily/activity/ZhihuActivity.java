package com.example.liujiachao.zhihudaily.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
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


import com.example.liujiachao.zhihudaily.service.DownloadService;
import com.example.liujiachao.zhihudaily.interfaces.MenuCallback;
import com.example.liujiachao.zhihudaily.entity.MyTheme;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.RecMenuAdapter;
import com.example.liujiachao.zhihudaily.entity.Theme;
import com.example.liujiachao.zhihudaily.entity.ThemeData;
import com.example.liujiachao.zhihudaily.fragment.ThemeFragment;
import com.example.liujiachao.zhihudaily.fragment.ZhihuHomeFragment;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeDataPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeDataView;

import java.util.ArrayList;
import java.util.List;

//访问网络，加载消息数据，并将其保存到数据库， 在该activity的生命周期中完成
public class ZhihuActivity extends AppCompatActivity implements ThemeDataView ,MenuCallback {

    final static int HOME_WHIET = Color.WHITE;

    private ThemeDataPresenter  themeDataPresenter;
    private RecyclerView swipe_rec_menu;
    private DrawerLayout drawerLayout;
    private boolean isTheme = false;
    private Toolbar toolbar;
    private ThemeData themeData;
    private List<MyTheme> myThemeList;
    private RecMenuAdapter recMenuAdapter;
    private TextView tv_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }


    private void initViews() {

        themeDataPresenter = new ThemeDataPresenter(this);
        themeDataPresenter.loadThemeData();

        setContentView(R.layout.main_activity);
        toolbar =(Toolbar)findViewById(R.id.common_toolbar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fragment, new ZhihuHomeFragment());
        fragmentTransaction.commit();

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
        tv_home = (TextView)findViewById(R.id.tv_home);

        recMenuAdapter = new RecMenuAdapter(this);
        swipe_rec_menu.setAdapter(recMenuAdapter);
        recMenuAdapter.setOnItemClickListener(new RecMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.linear_login:
                        Intent login_intent = new Intent(ZhihuActivity.this, LoginActivity.class);
                        startActivity(login_intent);
                        break;

                    case R.id.tv_collect:
                        Intent collect_intent = new Intent(ZhihuActivity.this, LoginActivity.class);
                        startActivity(collect_intent);
                        break;

                    case R.id.tv_download:
                        Intent download_intent = new Intent(ZhihuActivity.this, DownloadService.class);
                        startService(download_intent);
                        break;

                    case R.id.tv_home:
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_fragment, new ZhihuHomeFragment());
                        ft.commit();
                        drawerLayout.setBackgroundColor(Color.parseColor("#f0f0f0"));
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("首页");
                        break;
                    default:
                        break;
                }
            }
        });
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
        //该方法在子线程中运行
        recMenuAdapter.updateData(myThemeList);
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


    @Override
    public void onRedrawMenu(int position) {
        myThemeList.get(position - 1).setSubscribed(true);
        Toast.makeText(ZhihuActivity.this,"无法真正订阅",Toast.LENGTH_SHORT).show();
        recMenuAdapter.notifyDataSetChanged();//mark一下，此处要改，数据集顺序可能改变
    }

    @Override
    public void onHandleEvent(int position) {
        Theme theme = myThemeList.get(position - 1).getTheme();
        ThemeFragment themeFragment = new ThemeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("theme_id", theme.getId());
        themeFragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, themeFragment);
        ft.commit();

        getSupportActionBar().setTitle(theme.getName());
        drawerLayout.closeDrawers();
        tv_home.setBackgroundColor(HOME_WHIET);

        myThemeList.get(position - 1).setSelected(true);
        isTheme = true;
        myThemeList.get(position - 1).setSelected(true);

        for (MyTheme myTheme : myThemeList) {
            boolean tmp = myTheme == myThemeList.get(position - 1);
            myTheme.setSelected(tmp);
        }

        invalidateOptionsMenu();//菜单项已经改变，重新创造菜单
        recMenuAdapter.notifyDataSetChanged();


    }
}
