package com.example.liujiachao.zhihudaily;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;


import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeDataPresenter;
import com.example.liujiachao.zhihudaily.mvp.presenter.ZhihuNewsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeDataView;
import com.example.liujiachao.zhihudaily.mvp.view.ZhihuNewsView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

//访问网络，加载消息数据，并将其保存到数据库， 在该activity的生命周期中完成
public class ZhihuActivity extends AppCompatActivity implements ZhihuNewsView,ThemeDataView,OnShowNewsDetail,SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private RecyclerView menuList;
    private LinearLayoutManager layoutManager;
    private ZhihuListAdapter zhihuListAdapter;

    private ConvenientBanner banner;
    private ZhihuNewsPresenter zhihuNewsPresenter;
    private ThemeDataPresenter  themeDataPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;


    private RecyclerView swipe_rec_menu;
    private DrawerLayout drawerLayout;

    private ZhihuNewsModel zhihuNewsModel;

    private Toolbar toolbar;
    private Context context;

    private ThemeData themeData;
    private List<MyTheme> myThemeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

    }


    private void initViews() {

        themeDataPresenter = new ThemeDataPresenter(this);
        themeDataPresenter.loadThemeData();

        context = getBaseContext();
        setContentView(R.layout.home_page);
        toolbar =(Toolbar)findViewById(R.id.common_toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon();
        getSupportActionBar().setTitle("首页");

        //左上角图标可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        swipe_rec_menu.setAdapter(new RecMenuAdapter(myThemeList));


        layoutManager = new LinearLayoutManager(context);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        zhihuListAdapter = new ZhihuListAdapter(this);
        recyclerView.setAdapter(zhihuListAdapter);

        zhihuNewsPresenter = new ZhihuNewsPresenter(this);
        swipeRefreshLayout.setOnRefreshListener(this);




        initBanner();
        onRefresh();

        //先加载banner（作为recyclerView的第一项），之后再初始化banner
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    OnListScrolled();
                }
            }

        });





    }

    private void OnListScrolled() {
        initBanner();
        //滑到底了，得再重新加载数据
        int lastVisiPos = layoutManager.findLastVisibleItemPosition();
        if (lastVisiPos + 1 == zhihuListAdapter.getItemCount()) {
            zhihuNewsPresenter.loadBefore();
        }

    }

    private void initBanner() {
        if (banner == null && recyclerView.getChildCount() != 0 &&
                layoutManager.findFirstVisibleItemPosition() == 0) {
            banner = (ConvenientBanner)layoutManager.findViewByPosition(0);
            banner.setScrollDuration(500);
            banner.startTurning(2000);
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onShowNewsDetail(RecyclerView.ViewHolder holder) {

        //通过首页中载入的消息item个数，来确定fragment的数量
        if (holder instanceof ZhihuListAdapter.ItemViewHolder) {
            ArrayList<Integer> idList = new ArrayList<Integer>();
            List<NewsItem> newsItem = DB.findAll(NewsItem.class);
            for (NewsItem item : newsItem) {
                idList.add(item.getId());
            }

            ZhihuListAdapter.ItemViewHolder itemViewHolder = (ZhihuListAdapter.ItemViewHolder)holder;
            Intent intent = new Intent(ZhihuActivity.this,ZhihuNewsDetailActivity.class);
            intent.putIntegerArrayListExtra("all_id",idList);
            intent.putExtra("id", idList.indexOf(itemViewHolder.zhihuItemInfo.getId()));
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
             ZhihuActivity.this,itemViewHolder.mImage,"shared_img");

            startActivity(intent);

            itemViewHolder.mTitle.setTextColor(this.getResources().getColor(R.color.darker_gray));
        }
    }


    //swipefreshlayout.onfreshlistener类中的方法,监听到刷新手势时，该方法被调用
    @Override
    public void onRefresh() {
        zhihuNewsPresenter.loadLatest();

    }

    @Override
    public void showProgress() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }

    }

    @Override
    public void addZhihuNews(ZhihuJson zhihuJson) {
        zhihuListAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgress() {
        if(swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void loadFailed(String msg) {
        Snackbar.make(recyclerView,"网络出现了点问题",Snackbar.LENGTH_LONG);
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
}
