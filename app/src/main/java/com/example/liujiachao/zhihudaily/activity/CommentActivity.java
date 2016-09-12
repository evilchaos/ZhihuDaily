package com.example.liujiachao.zhihudaily.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.mvp.presenter.LongCommentsPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.LongCommentsView;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class CommentActivity extends AppCompatActivity implements LongCommentsView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int story_id = intent.getIntExtra("story_id", 0);

        LongCommentsPresenter longCommentsPresenter = new LongCommentsPresenter(this);
        longCommentsPresenter.loadLongComments(story_id);




    }

    @Override
    public void showLongComments(Comments comments) {


    }
}
