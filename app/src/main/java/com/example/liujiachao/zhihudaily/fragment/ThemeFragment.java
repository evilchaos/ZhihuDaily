package com.example.liujiachao.zhihudaily.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujiachao.zhihudaily.activity.EditorDetailActivity;
import com.example.liujiachao.zhihudaily.activity.ZhihuNewsDetailActivity;
import com.example.liujiachao.zhihudaily.entity.Edit;
import com.example.liujiachao.zhihudaily.entity.MyTheme;
import com.example.liujiachao.zhihudaily.entity.ThemeItem;
import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.ThemeListAdapter;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.interfaces.OnShowThemeDetail;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeContentPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeContentView;
import com.example.liujiachao.zhihudaily.utils.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeFragment extends Fragment implements ThemeContentView ,OnShowThemeDetail {

    final static int THEME_LIST_MSG = 2;
    private Context context;
    private ThemeListAdapter themeListAdapter;
    private RecyclerView themeListView;
    private int theme_id;
    private ThemeContentPresenter themeContentPresenter;
    private Handler themeListHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        View themeFragmentView = inflater.inflate(R.layout.navi_fragment,null);
        //获取主题列表名
        String name = getArguments().getString("name");
        themeListAdapter = new ThemeListAdapter(this,name);
        receiveData();
        themeListView = (RecyclerView)themeFragmentView.findViewById(R.id.recycler_view);
        themeListView.setAdapter(themeListAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(themeListView.getContext(),LinearLayoutManager.VERTICAL,false);
        themeListView.setLayoutManager(manager);

        themeContentPresenter = new ThemeContentPresenter(this);
        theme_id = getArguments().getInt("theme_id");
        themeContentPresenter.loadThemeContent(theme_id);
        return themeFragmentView;

    }

    @Override
    public void addThemeContent(ThemeContent themeContent) {

        ArrayList<ThemeContent> themeContents = new ArrayList<>();
        themeContents.add(themeContent);
        sendMessage(themeContents);
    }

    private void sendMessage(ArrayList<ThemeContent> themeContents) {
        Message msg;
        msg = Message.obtain();
        msg.what = THEME_LIST_MSG;
        Bundle bundle = new Bundle();
        bundle.putSerializable("theme_content", themeContents);
        msg.setData(bundle);
        themeListHandler.sendMessage(msg);
    }

    private void receiveData() {
        themeListHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case THEME_LIST_MSG:
                        ArrayList<ThemeContent> themeArrayList = (ArrayList<ThemeContent>) msg.getData().getSerializable("theme_content");
                        themeListAdapter.updateData(themeArrayList);
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }


    @Override
    public void onShowThemeDetail(RecyclerView.ViewHolder holder, List<ThemeItem> themeItemList, int position) {
        List<ThemeItem> themeItems = themeItemList;
        ArrayList<Integer> themeDetailIds = new ArrayList<Integer>();
        for (ThemeItem themeItem : themeItemList) {
            themeDetailIds.add(themeItem.getId());
        }

        ThemeListAdapter.ThemeItemViewHolder themeItemViewHolder = (ThemeListAdapter.ThemeItemViewHolder) holder;
        Intent intent = new Intent(getActivity(), ZhihuNewsDetailActivity.class);
        intent.putIntegerArrayListExtra("all_id",themeDetailIds);
        intent.putExtra("id",themeDetailIds.indexOf(themeItems.get(position).getId()));
        startActivity(intent);

        themeItemViewHolder.mTitle.setTextColor(this.getResources().getColor(R.color.darker_gray));
    }
}
