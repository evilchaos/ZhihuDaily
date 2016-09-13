package com.example.liujiachao.zhihudaily.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.CommentAdapter;
import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.mvp.presenter.LongCommentsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.LongCommentsView;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class CommentActivity extends AppCompatActivity implements LongCommentsView {

    private RecyclerView longCommentRecycler;
    private Comments longComments;
    private TextView longCommentNum;
    private TextView shortCommentNum;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int story_id = intent.getIntExtra("story_id", 0);
        int long_comments = intent.getIntExtra("long_comments", 0);
        int short_comments = intent.getIntExtra("short_comments", 0);
        int total_comments = intent.getIntExtra("comments",0);

        LongCommentsPresenter longCommentsPresenter = new LongCommentsPresenter(this);
        longCommentsPresenter.loadLongComments(story_id);

        setContentView(R.layout.comment_activity);
        toolbar = (Toolbar)findViewById(R.id.comment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back));

        longCommentRecycler = (RecyclerView)findViewById(R.id.rv_long_comment);
        longCommentNum = (TextView)findViewById(R.id.tv_long_comment_num);
        shortCommentNum = (TextView)findViewById(R.id.tv_short_comment_num);

        toolbar.setTitle(String.format("%1$d条点评",total_comments));
        longCommentNum.setText(String.format("%1$d条长评",long_comments));
        shortCommentNum.setText(String.format("%1$d条短评",short_comments));

        if (longComments != null ) {
            CommentAdapter longCommentAdapter = new CommentAdapter(longComments.getComments());
            longCommentRecycler.setAdapter(longCommentAdapter);
        }


    }

    @Override
    public void showLongComments(Comments comments) {
        this.longComments = comments;
    }
}
