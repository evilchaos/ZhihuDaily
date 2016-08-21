package com.example.liujiachao.zhihudaily;

import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import static com.example.liujiachao.zhihudaily.ZhihuNewsDetailFragment.*;

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

       return ZhihuNewsDetailFragment.newInstance(idList.get(position));
    }

    @Override
    public int getCount() {
        return idList.size();
    }
}
