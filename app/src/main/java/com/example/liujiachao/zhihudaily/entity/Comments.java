package com.example.liujiachao.zhihudaily.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class Comments implements Serializable {
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
