package com.example.liujiachao.zhihudaily.entity;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeContent implements Serializable {

    @PrimaryKey
    private String name; // 该主题日报的名称
    private RealmList<ThemeItem> stories; // 该主题日报中的文章列表
    private String background; //该主题日报的背景图片（大图）
    private List<Edit> editors; //该主题日报的编辑
    private String image_source;// 图像的版权信息
    private String description;//该主题日报的介绍

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThemeContent() {
    }

    public RealmList<ThemeItem> getStories() {
        return stories;
    }

    public void setStories(RealmList<ThemeItem> stories) {
        this.stories = stories;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edit> getEditors() {
        return editors;
    }

    public void setEditors(List<Edit> editors) {
        this.editors = editors;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }
}
