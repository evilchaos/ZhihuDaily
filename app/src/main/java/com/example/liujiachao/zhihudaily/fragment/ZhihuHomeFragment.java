package com.example.liujiachao.zhihudaily.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.liujiachao.zhihudaily.entity.Edit;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.entity.ZhihuItemInfo;
import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.activity.ZhihuNewsDetailActivity;
import com.example.liujiachao.zhihudaily.adapter.ZhihuListAdapter;
import com.example.liujiachao.zhihudaily.entity.NewsItem;
import com.example.liujiachao.zhihudaily.entity.ZhihuJson;
import com.example.liujiachao.zhihudaily.mvp.presenter.ZhihuNewsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ZhihuNewsView;
import com.example.liujiachao.zhihudaily.utils.API;
import com.example.liujiachao.zhihudaily.utils.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiachao on 2016/9/2.
 */
public class ZhihuHomeFragment extends Fragment implements ZhihuNewsView,OnShowNewsDetail,SwipeRefreshLayout.OnRefreshListener{

    public final static int NEWS_LIST_MSG = 3;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ZhihuListAdapter zhihuListAdapter;
    private ConvenientBanner banner;
    private ZhihuNewsPresenter zhihuNewsPresenter;

    private LinearLayoutManager layoutManager;
    private Handler mNewsHandler ;
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

        receiveData();

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


    private List<NewsItem> getItemList(ZhihuJson zhihuJson) {
        List<NewsItem> list = new ArrayList<>();
        NewsItem newsItem = new NewsItem();
        String date = zhihuJson.getDate();

        List<ZhihuItemInfo> stories = zhihuJson.getStories();
        for(ZhihuItemInfo info : stories) {
            NewsItem tmp = new NewsItem();
            tmp.setDate(date);
            tmp.setTitle(info.getTitle());
            tmp.setId(info.getId());
            tmp.setImage(info.getImages().get(0).getVal());
            list.add(tmp);

        }
        return list;
    }

    private void receiveData() {
        mNewsHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case NEWS_LIST_MSG:
                        //更新数据处理逻辑
                        ZhihuJson zhihuJson = (ZhihuJson)msg.getData().getSerializable("news_list");
                        int type = msg.getData().getInt("url_type");
                        if (type == API.TYPE_LATEST) {
                            if (zhihuJson.getTop_stories() != null) {
                                zhihuListAdapter.updateTopStory(zhihuJson.getTop_stories());
                            }

                            if (zhihuJson.getStories() != null) {
                                List<NewsItem> newsItems = getItemList(zhihuJson);
                                zhihuListAdapter.updateLatest(newsItems);
                            }
                        } else if (type == API.TYPE_BEFORE){

                            if (zhihuJson.getStories() != null) {
                                List<NewsItem> mItems = getItemList(zhihuJson);
                                zhihuListAdapter.loadOldData(mItems);
                            }
                    }
                        break;
                }
                super.handleMessage(msg);
            }
        };
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
    public void addZhihuNews(int type ,ZhihuJson zhihuJson) {
        Message msg = Message.obtain();
        msg.what = NEWS_LIST_MSG;
        Bundle bundle = new Bundle();
        bundle.putInt("url_type",type);
        bundle.putSerializable("news_list",zhihuJson);
        msg.setData(bundle);
        mNewsHandler.sendMessage(msg);
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
