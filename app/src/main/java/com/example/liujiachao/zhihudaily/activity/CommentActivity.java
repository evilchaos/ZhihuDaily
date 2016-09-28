package com.example.liujiachao.zhihudaily.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.liujiachao.zhihudaily.mvp.view.CommentsView;
import com.example.liujiachao.zhihudaily.mvp.view.LongCommentsView;
import com.example.liujiachao.zhihudaily.mvp.view.ShortCommentsView;
import com.example.liujiachao.zhihudaily.utils.API;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class CommentActivity extends AppCompatActivity implements CommentsView {

    public final static int LONG_COMM_MSG = 0;
    public final static int SHORT_COMM_MSG = 1;
    private RecyclerView longCommentRecycler;
    private RecyclerView shortCommentRecycler;
    private ScrollView scrollView;
    private TextView longCommentNum;
    private TextView shortCommentNum;
    private Toolbar toolbar;
    private boolean isExpandable = false;

    private int story_id;
    private int long_comments;
    private int short_comments;
    private int total_comments;
    private CommentAdapter longCommentAdapter = new CommentAdapter();
    private CommentAdapter shortCommentAdapter;

    private Handler mCommentHandler;

    private ShortCommentsPresenter shortCommentsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        Intent intent = getIntent();
        story_id = intent.getIntExtra("story_id", 0);
        initToolbar();
        setCommentTextView(intent);
        receiveData();

        shortCommentsPresenter = new ShortCommentsPresenter(this);
        LongCommentsPresenter longCommentsPresenter = new LongCommentsPresenter(this);
        longCommentsPresenter.loadLongComments(story_id);

        if (long_comments > 0 ) {
            longCommentRecycler.setAdapter(longCommentAdapter);
        }

        if (short_comments > 0 ){
            shortCommentNum.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                @Override
                public void onClick(View v) {
                    if (!isExpandable) {
                        isExpandable = true;
                        shortCommentNum.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,
                                getResources().getDrawable(R.drawable.comment_icon_expand),null);
                        // 主线程需要使用子线程初始化的变量，如何保证在主线程使用该变量时，该变量已经被子线程初始化
                        shortCommentsPresenter.loadShortComments(story_id);
                        CommentAdapter shortCommentAdapter = new CommentAdapter();
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

    private void receiveData() {
        mCommentHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case LONG_COMM_MSG:
                        //数据处理逻辑
                        Comments long_comments = (Comments)msg.getData().getSerializable("comments");
                        longCommentAdapter.updateData(long_comments.getComments());
                        break;
                    case SHORT_COMM_MSG:
                        Comments short_comments = (Comments)msg.getData().getSerializable("comments");
                        shortCommentAdapter.updateData(short_comments.getComments());
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    private void setCommentTextView(Intent intent) {
        long_comments = intent.getIntExtra("long_comments", 0);
        short_comments = intent.getIntExtra("short_comments", 0);
        total_comments = intent.getIntExtra("comments",0);
        toolbar.setTitle(String.format("%1$d条点评", total_comments));
        longCommentNum.setText(String.format("%1$d条长评", long_comments));
        shortCommentNum.setText(String.format("%1$d条短评", short_comments));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));
    }

    private void initViews() {
        setContentView(R.layout.comment_activity);
        toolbar = (Toolbar)findViewById(R.id.comment_toolbar);
        longCommentRecycler = (RecyclerView)findViewById(R.id.rv_long_comment);
        shortCommentRecycler = (RecyclerView)findViewById(R.id.rv_short_comment);
        longCommentNum = (TextView)findViewById(R.id.tv_long_comment_num);
        shortCommentNum = (TextView)findViewById(R.id.tv_short_comment_num);
        scrollView = (ScrollView)findViewById(R.id.scroll_view);

    }

    @Override
    public void showComments(Comments comments,int comment_type) {
        Message msg = Message.obtain();
        if (comment_type == API.LONG_COMMENT_TAG) {
            msg.what = LONG_COMM_MSG;
        } else {
            msg.what = SHORT_COMM_MSG;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("comments",comments);
        msg.setData(bundle);
        mCommentHandler.sendMessage(msg);
    }
}
