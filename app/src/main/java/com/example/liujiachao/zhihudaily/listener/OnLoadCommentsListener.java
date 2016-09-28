package com.example.liujiachao.zhihudaily.listener;

import com.example.liujiachao.zhihudaily.entity.Comments;

/**
 * Created by liujiachao on 2016/9/28.
 */
public interface OnLoadCommentsListener {
    public void onLoadCommentsSuccess(Comments response,int comment_type);
}
