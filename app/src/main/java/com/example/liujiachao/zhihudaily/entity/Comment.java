package com.example.liujiachao.zhihudaily.entity;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class Comment {
    private String author;
    private int id;
    private String content;
    private int likes;
    private long time;
    private String avatar;
    private Reply reply_to;

    public Reply getReply_to() {
        return reply_to;
    }

    public void setReply_to(Reply reply_to) {
        this.reply_to = reply_to;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    private boolean voted;

    public Comment() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
