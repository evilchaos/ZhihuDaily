package com.example.liujiachao.zhihudaily;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by liujiachao on 2016/8/18.
 */
public class NewsDetailAdapter extends FragmentPagerAdapter {


    private ArrayList<Integer> idList;
    public NewsDetailAdapter(FragmentManager fm,ArrayList<Integer> idList) {
        super(fm);
        this.idList = idList;
    }

    @Override
    public Fragment getItem(int position) {

        return null;
    }

    @Override
    public int getCount() {
        return idList.size();
    }
}
