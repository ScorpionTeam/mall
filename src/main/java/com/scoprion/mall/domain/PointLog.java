package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author by kunlun
 * @created on 2017/11/7.
 */
public class PointLog {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    @JSONField(name = "user_id")
    private String userId;

    /**
     * 动作
     * 0 扣减积分
     * 1 返还积分
     */
    private String action;

    /**
     * 当前积分
     */
    @JSONField(name = "current_point")
    private int currentPoint;

    /**
     * 操作积分
     */
    @JSONField(name = "operate_point")
    private int operatePoint;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public int getOperatePoint() {
        return operatePoint;
    }

    public void setOperatePoint(int operatePoint) {
        this.operatePoint = operatePoint;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "PointLog{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", action='" + action + '\'' +
                ", currentPoint=" + currentPoint +
                ", operatePoint=" + operatePoint +
                ", createDate=" + createDate +
                '}';
    }
}
