package com.example.liujiachao.zhihudaily.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.activity.LoginActivity;
import com.example.liujiachao.zhihudaily.adapter.RecMenuAdapter;
import com.example.liujiachao.zhihudaily.entity.MyTheme;
import com.example.liujiachao.zhihudaily.entity.Theme;
import com.example.liujiachao.zhihudaily.entity.ThemeData;
import com.example.liujiachao.zhihudaily.interfaces.MenuCallback;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeDataPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeDataView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by evilchaos on 16/9/18.
 */
public class NavigationDrawerFragment extends Fragment implements ThemeDataView,MenuCallback {

    final static int THEME_DATA_MSG = 1;
    private ThemeDataPresenter  themeDataPresenter;
    private  RecMenuAdapter adapter;
    private Handler themeDataHandler;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<MyTheme> themeDatas;
    private FragmentActivity mActivity;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        mActivity = getActivity();
        setUpRecyclerView(view);

        receiveData();

        return view;
    }

    private void setUpRecyclerView(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.drawerList);
        adapter =  new RecMenuAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        adapter.setOnItemClickListener(new RecMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                switch (view.getId()) {
                    case R.id.linear_login:
                        Intent login_intent = new Intent(mActivity, LoginActivity.class);
                        startActivity(login_intent);
                        break;

                    case R.id.tv_collect:
                        Intent collect_intent = new Intent(mActivity, LoginActivity.class);
                        startActivity(collect_intent);
                        break;

                    case R.id.tv_download:
                        Toast.makeText(mActivity, "暂无离线下载功能", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.tv_home:
                        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_fragment, new ZhihuHomeFragment());
                        ft.commit();
                        view.findViewById(R.id.tv_home).setBackgroundColor(Color.parseColor("#f0f0f0"));
                        mDrawerLayout.closeDrawers();
                        ((AppCompatActivity) mActivity).getSupportActionBar().setTitle("首页");

                        for (MyTheme myTheme : themeDatas) {
                            myTheme.setSelected(false);
                        }
                        adapter.setHomeSelected(true);
                        adapter.updateData(themeDatas);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void receiveData() {
        themeDataHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case THEME_DATA_MSG :
                        ArrayList<MyTheme> themeArrayList = (ArrayList<MyTheme>) msg.getData().getSerializable("theme_list");
                        adapter.updateData(themeArrayList);
                        break;
                }
                super.handleMessage(msg);
            }
        };

        themeDataPresenter = new ThemeDataPresenter(this);
        themeDataPresenter.loadThemeData();
    }


    public void setUpDrawer(int fragmentId,DrawerLayout drawerLayout,Toolbar toolbar) {
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    @Override
    public void onRedrawMenu(int position) {
        List<MyTheme> themeMenuData = adapter.getThemeMenuData();
        themeMenuData.get(position - 1).setSubscribed(true);
        Toast.makeText(getActivity(), "无法真正订阅", Toast.LENGTH_SHORT).show();
        Collections.sort(themeMenuData, comparator);
        adapter.notifyDataSetChanged();
    }

    Comparator<MyTheme> comparator = new Comparator<MyTheme>() {
        @Override
        public int compare(MyTheme lhs, MyTheme rhs) {

            if (lhs.isSubscribed() != rhs.isSubscribed()) {
                if (lhs.isSubscribed() == true && rhs.isSubscribed() == false) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (lhs.getSerial_num() < rhs.getSerial_num()) {
                return -1;
            } else if (lhs.getSerial_num() > rhs.getSerial_num()) {
                return 1;
            }

            return 0;
        }
    };

    @Override
    public void onHandleEvent(int position) {

        List<MyTheme> themeMenuData = adapter.getThemeMenuData();
        Theme theme = themeMenuData.get(position - 1).getTheme();
        ThemeFragment themeFragment = new ThemeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("theme_id", theme.getId());
        bundle.putString("name", theme.getName());
        themeFragment.setArguments(bundle);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, themeFragment);
        ft.commit();


        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(theme.getName());

        mDrawerLayout.closeDrawers();
        themeMenuData.get(position - 1).setSelected(true);
        for (MyTheme myTheme : themeMenuData) {
            boolean tmp = myTheme == themeMenuData.get(position - 1);
            myTheme.setSelected(tmp);
        }
        //invalidateOptionsMenu();//菜单项已经改变，重新创造菜单
        adapter.setHomeSelected(false);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void PassThemeDataToActivity(ThemeData themeData) {

        ArrayList<MyTheme> themeDatas = initThemeList(themeData);
        initData(themeDatas);
        sendMessage(themeDatas);
    }

    private void sendMessage(ArrayList<MyTheme> themeDatas) {
        Message msg;
        msg = Message.obtain();
        msg.what = THEME_DATA_MSG;
        Bundle bundle = new Bundle();
        bundle.putSerializable("theme_list", themeDatas);
        msg.setData(bundle);
        themeDataHandler.sendMessage(msg);
    }

    private void initData(ArrayList<MyTheme> themeDatas) {
        this.themeDatas = themeDatas;
    }

    private ArrayList<MyTheme> initThemeList(ThemeData themeData) {

        ArrayList<MyTheme> myThemeList = new ArrayList<>();
        int serial_num = 0;
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
        return myThemeList;
    }
}
