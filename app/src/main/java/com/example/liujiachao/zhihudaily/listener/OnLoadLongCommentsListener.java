package com.example.liujiachao.zhihudaily.listener;

import com.example.liujiachao.zhihudaily.entity.Comment;
import com.example.liujiachao.zhihudaily.entity.Comments;

import java.util.List;

/**
 * Created by liujiachao on 2016/9/12.
 */
public interface OnLoadLongCommentsListener {
    void onLoadLongCommentsSuccess(Comments response);
}
