package com.example.liujiachao.zhihudaily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.liujiachao.zhihudaily.mvp.presenter.ZhihuNewsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ZhihuNewsView;
import com.example.liujiachao.zhihudaily.utils.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiachao on 2016/9/2.
 */
public class ZhihuHomeFragment extends Fragment implements ZhihuNewsView,OnShowNewsDetail,SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ZhihuListAdapter zhihuListAdapter;
    private ConvenientBanner banner;
    private ZhihuNewsPresenter zhihuNewsPresenter;

    private LinearLayoutManager layoutManager;
    Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        View contentFragmentView = inflater.inflate(R.layout.navi_fragment,null);
        swipeRefreshLayout = (SwipeRefreshLayout)contentFragmentView.findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) contentFragmentView.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(context);

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




        return contentFragmentView;
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


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRefresh() {
        zhihuNewsPresenter.loadLatest();
    }

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
            Intent intent = new Intent(getActivity(),ZhihuNewsDetailActivity.class);
            intent.putIntegerArrayListExtra("all_id",idList);
            intent.putExtra("id", idList.indexOf(itemViewHolder.zhihuItemInfo.getId()));
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(),itemViewHolder.mImage,"shared_img");

            startActivity(intent);

            itemViewHolder.mTitle.setTextColor(this.getResources().getColor(R.color.darker_gray));
        }

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
}