package com.example.liujiachao.zhihudaily.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.CommentAdapter;
import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.mvp.presenter.LongCommentsPresenter;
import com.example.liujiachao.zhihudaily.mvp.presenter.ShortCommentsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.LongCommentsView;
import com.example.liujiachao.zhihudaily.mvp.view.ShortCommentsView;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class CommentActivity extends AppCompatActivity implements LongCommentsView,ShortCommentsView {

    private RecyclerView longCommentRecycler;
    private RecyclerView shortCommentRecycler;
    private ScrollView scrollView;
    private Comments longComments;
    private Comments shortComments;
    private TextView longCommentNum;
    private TextView shortCommentNum;
    private Toolbar toolbar;
    private boolean isExpandable = false;

    private int story_id;

    private ShortCommentsPresenter shortCommentsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        story_id = intent.getIntExtra("story_id", 0);
        int long_comments = intent.getIntExtra("long_comments", 0);
        int short_comments = intent.getIntExtra("short_comments", 0);
        int total_comments = intent.getIntExtra("comments",0);

        LongCommentsPresenter longCommentsPresenter = new LongCommentsPresenter(this);
        longCommentsPresenter.loadLongComments(story_id);
        shortCommentsPresenter = new ShortCommentsPresenter(this);

        setContentView(R.layout.comment_activity);
        toolbar = (Toolbar)findViewById(R.id.comment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));

        longCommentRecycler = (RecyclerView)findViewById(R.id.rv_long_comment);
        shortCommentRecycler = (RecyclerView)findViewById(R.id.rv_short_comment);


        longCommentNum = (TextView)findViewById(R.id.tv_long_comment_num);
        shortCommentNum = (TextView)findViewById(R.id.tv_short_comment_num);
        scrollView = (ScrollView)findViewById(R.id.scroll_view);

        toolbar.setTitle(String.format("%1$d条点评", total_comments));
        longCommentNum.setText(String.format("%1$d条长评",long_comments));
        shortCommentNum.setText(String.format("%1$d条短评",short_comments));

        if (longComments != null ) {
            CommentAdapter longCommentAdapter = new CommentAdapter(longComments.getComments());
            longCommentRecycler.setAdapter(longCommentAdapter);
        }

        if (short_comments > 0 ){
            shortCommentNum.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public void onClick(View v) {
                    if (!isExpandable) {
                        isExpandable = true;
                        shortCommentNum.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.comment_icon_expand),null);
                        // 主线程需要使用子线程初始化的变量，如何保证在主线程使用该变量时，该变量已经被子线程初始化
                        shortCommentsPresenter.loadShortComments(story_id);
                        CommentAdapter shortCommentAdapter = new CommentAdapter(shortComments.getComments());
                        shortCommentRecycler.setAdapter(shortCommentAdapter);
                        scrollView.smoothScrollBy(0,(int)shortCommentNum.getY());
                    } else {
                        isExpandable = false;
                        shortCommentRecycler.setVisibility(View.GONE);
                        scrollView.smoothScrollTo(0,0);
                    }

                }
            });
        }

    }

    @Override
    public void showLongComments(Comments comments) {
        this.longComments = comments;
    }

    @Override
    public void showShortComments(Comments comments) {
        this.shortComments = comments;
    }
}
