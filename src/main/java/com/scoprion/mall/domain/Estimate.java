package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author fk
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
    @JSONField(name = "good_id")
    private Long goodId;

    /**
     * 微信Code
     */
    @JSONField(name = "wx_code")
    private String wxCode;
    /**
     * 签收后用户评价
     */
    private String message;

    /**
     * 用户ID
     */
    @JSONField(name = "user_id")
    private String userId;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "update_date")
    private Date updateDate;

    /**
     * 投诉
     */
    private String complain;

    /**
     * 昵称
     */
    private String nick_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
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

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    @Override
    public String toString() {
        return "Estimate{" +
                "id=" + id +
                ", goodId=" + goodId +
                ", wxCode='" + wxCode + '\'' +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", complain='" + complain + '\'' +
                ", nick_name='" + nick_name + '\'' +
                '}';
    }
}
