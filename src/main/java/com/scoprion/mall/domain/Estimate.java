package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by fk
 * @date 2017/11/9.
 */
public class Estimate {

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品ID
     */
    private String goodId;

    /**
     * 签收后用户评价
     */
    private String message;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 投诉
     */
    private String complain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    @Override
    public String toString() {
        return "Estimate{" +
                "id=" + id +
                ", goodId='" + goodId + '\'' +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", complain='" + complain + '\'' +
                '}';
    }
}
