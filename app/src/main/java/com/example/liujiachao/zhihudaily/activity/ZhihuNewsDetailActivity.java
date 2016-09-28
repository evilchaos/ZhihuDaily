package com.example.liujiachao.zhihudaily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liujiachao.zhihudaily.adapter.NewsDetailAdapter;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.entity.StoryExtra;
import com.example.liujiachao.zhihudaily.mvp.presenter.StoryExtraPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.StoryExtraView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by liujiachao on 2016/7/25.
 */
public class ZhihuNewsDetailActivity extends AppCompatActivity implements OnPageChangeListener ,StoryExtraView, View.OnClickListener {

    private TextView backMenu;
    private TextView shareMenu;
    private TextView collectMenu;
    private TextView commentMenu;
    private TextView commentNumMenu;
    private TextView praiseMenu;
    private TextView praiseNumMenu;
    private RelativeLayout toolBar;
    ViewPager viewPager;
    StoryExtra storyExtra;
    StoryExtraPresenter presenter;

    private ArrayList<Integer> idList;
        private int id;

    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {

        idList =  getIntent().getIntegerArrayListExtra("all_id");
        id  = getIntent().getIntExtra("id", 0);
        setContentView(R.layout.zhihu_detail_activity);
        backMenu = (TextView) findViewById(R.id.back);
        shareMenu = (TextView)findViewById(R.id.share);
        collectMenu = (TextView)findViewById(R.id.collect);
        commentMenu = (TextView)findViewById(R.id.comment);
        commentNumMenu = (TextView)findViewById(R.id.comment_num);
        praiseMenu = (TextView)findViewById(R.id.praise);
        praiseNumMenu = (TextView)findViewById(R.id.praise_num);
        toolBar = (RelativeLayout)findViewById(R.id.toolbar);

        //toolBar.setOnClickListener(this);
        backMenu.setOnClickListener(this);
        shareMenu.setOnClickListener(this);
        collectMenu.setOnClickListener(this);
        commentMenu.setOnClickListener(this);
        praiseMenu.setOnClickListener(this);

        presenter = new StoryExtraPresenter(this);
        presenter.loadStoryExtra(idList.get(id));

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new NewsDetailAdapter(getSupportFragmentManager(), idList));
        viewPager.setCurrentItem(id);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //切换fragment时，对应的menu bar上的数据也要更新
    @Override
    public void onPageSelected(int position) {
        id = position;
        presenter.loadStoryExtra(idList.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void showExtraInfo(StoryExtra storyExtra) {

        long_comments = storyExtra.getLong_comments();
        short_comments = storyExtra.getShort_comments();
        comments = storyExtra.getComments();
        popularity = storyExtra.getPopularity();
        String comment_num = comments > 1000 ? new DecimalFormat("#.0").format(((float)comments)/1000) + "K" :comments + "";
        commentNumMenu.setText(comment_num);
        String praise_num = popularity > 1000 ? new DecimalFormat("#.0").format(((float)popularity)/1000) + "K": popularity + "";
        praiseNumMenu.setText(praise_num);
    }

    @Override
    public void showExtraFailed(String msg) {

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                //逻辑
                finish();
                break;
            case R.id.share:
                //分享功能
                Snackbar.make(toolBar, "暂无此功能", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.collect:
                //收藏功能
                Intent intent = new Intent(ZhihuNewsDetailActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.comment:
                //评论
                int story_id = idList.get(id);
                Intent comm_intent = new Intent(ZhihuNewsDetailActivity.this,CommentActivity.class);
                comm_intent.putExtra("long_comments",long_comments);
                comm_intent.putExtra("short_comments",short_comments);
                comm_intent.putExtra("comments",comments);
                comm_intent.putExtra("story_id",story_id);
                startActivity(comm_intent);
                break;
            case R.id.praise:
                //点赞
                break;
        }
    }
}
