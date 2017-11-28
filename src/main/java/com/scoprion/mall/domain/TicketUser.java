package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <优惠券-用户关联关系>
 * @date 2017-11-14 15:44
 */
public class TicketUser {

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
     * 优惠券快照id
     */
    @JSONField(name = "snapshot_id")
    private Long snapshotId;
    /**
     * 优惠券数量
     */
    private int num;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    /**
     * 使用状态 0未使用 1已使用
     */
    private String status;


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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Long snapshotId) {
        this.snapshotId = snapshotId;
    }


    @Override
    public String toString() {
        return "TicketUser{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", snapshotId=" + snapshotId +
                ", num=" + num +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                '}';
    }
}
