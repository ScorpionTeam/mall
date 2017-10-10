package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created on 2017/9/29.
 */
public class Banner {

    //主键
    private Long id;

    //banner名称
    private String name;

    //类型  1 首页轮播图
    private String type;

    //banner图地址
    private String imgurl;

    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    //排序
    private int sort;

    //连接跳转地址
    private String hoverUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getHoverUrl() {
        return hoverUrl;
    }

    public void setHoverUrl(String hoverUrl) {
        this.hoverUrl = hoverUrl;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", createDate=" + createDate +
                ", sort=" + sort +
                ", hoverUrl='" + hoverUrl + '\'' +
                '}';
    }
}
