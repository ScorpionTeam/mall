package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created on 2017/9/28.
 */
public class Point {

    //主键
    private Long id;

    //用户id
    private Long userId;

    //积分
    private int point;

    //等级
    //1   初级 0~1000积分
    //2   白银 1001~10000积分
    //3   黄金 10001~30000积分
    //4   白金 30001~100000积分
    //5   钻石 100001~100000积分
    private int level;

    //最后更新时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
