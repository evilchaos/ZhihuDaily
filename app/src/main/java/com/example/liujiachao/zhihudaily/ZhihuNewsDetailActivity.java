package com.example.liujiachao.zhihudaily;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.liujiachao.zhihudaily.mvp.presenter.StoryExtraPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.StoryExtraView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by liujiachao on 2016/7/25.
 */
public class ZhihuNewsDetailActivity extends AppCompatActivity implements OnPageChangeListener ,StoryExtraView {

    private TextView backMenu;
    private TextView shareMenu;
    private TextView collectMenu;
    private TextView commentMenu;
    private TextView commentNumMenu;
    private TextView praiseMenu;
    private TextView praiseNumMenu;
    ViewPager viewPager;

    private ArrayList<Integer> idList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    private void initViews() {

        idList =  getIntent().getIntegerArrayListExtra("all_id");
        int id  = getIntent().getIntExtra("id",0);
        StoryExtra storyExtra = DB.getById(id,StoryExtra.class);

        setContentView(R.layout.zhihu_detail_activity);
        backMenu = (TextView) findViewById(R.id.back);
        shareMenu = (TextView)findViewById(R.id.share);
        collectMenu = (TextView)findViewById(R.id.collect);
        commentMenu = (TextView)findViewById(R.id.comment);
        commentNumMenu = (TextView)findViewById(R.id.comment_num);
        praiseMenu = (TextView)findViewById(R.id.praise);
        praiseNumMenu = (TextView)findViewById(R.id.praise_num);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new NewsDetailAdapter(getSupportFragmentManager(),idList));
        viewPager.setCurrentItem(id);


        StoryExtraPresenter presenter = new StoryExtraPresenter(this);
        if(storyExtra == null) {
            presenter.loadStoryExtra(id);
        } else {
            showExtraInfo(storyExtra);
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void showExtraInfo(StoryExtra storyExtra) {

        int comments = storyExtra.getComments();
        int praises = storyExtra.getPopularity();
        String comment_num = comments > 1000 ? new DecimalFormat("#.0").format(((float)comments)/1000) + "K" :comments + "";
        commentNumMenu.setText(comment_num);
        String praise_num = praises > 1000 ? new DecimalFormat("#.0").format(((float)praises)/1000) + "K": praises + "";
        praiseNumMenu.setText(praise_num);

    }

    @Override
    public void showExtraFailed(String msg) {

    }
}
