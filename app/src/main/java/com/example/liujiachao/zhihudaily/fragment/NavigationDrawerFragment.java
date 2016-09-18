package com.example.liujiachao.zhihudaily.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.RecMenuAdapter;
import com.example.liujiachao.zhihudaily.entity.MyTheme;
import com.example.liujiachao.zhihudaily.entity.Theme;
import com.example.liujiachao.zhihudaily.entity.ThemeData;
import com.example.liujiachao.zhihudaily.interfaces.MenuCallback;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeDataPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeDataView;

import java.util.ArrayList;

/**
 * Created by evilchaos on 16/9/18.
 */
public class NavigationDrawerFragment extends Fragment implements ThemeDataView,MenuCallback {

    private ThemeDataPresenter  themeDataPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.drawerList);
        RecMenuAdapter adapter =  new RecMenuAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        themeDataPresenter = new ThemeDataPresenter(this);
        themeDataPresenter.loadThemeData();

        return view;
    }

    @Override
    public void onRedrawMenu(int position) {

    }

    @Override
    public void onHandleEvent(int position) {

    }

    @Override
    public void PassThemeDataToActivity(ThemeData themeData) {

        int serial_num = 0;
        ArrayList<MyTheme> myThemeList = new ArrayList<>();
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
        recMenuAdapter.updateData(myThemeList);
        Message msg;
        msg = Message.obtain();
        msg.what = THEME_DATA_MSG;
        Bundle bundle = new Bundle();
        bundle.putSerializable("theme_list", myThemeList);
        msg.setData(bundle);
        themeDataHandler.sendMessage(msg);

    }
}
