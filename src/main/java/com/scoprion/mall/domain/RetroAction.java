package com.scoprion.mall.domain;

import java.util.Date;

/**
 * 反馈
 *
 * @author by kunlun
 * @created on 2017/11/16.
 */
public class RetroAction {

    /**
     * 主键
     */
    private Long id;

    /**
     * 反馈类型 1) 我是买家 2) 我是卖家
     */
    private String retroActionType;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 用户ID
     */
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRetroActionType() {
        return retroActionType;
    }

    public void setRetroActionType(String retroActionType) {
        this.retroActionType = retroActionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RetroAction{" +
                "id=" + id +
                ", retroActionType='" + retroActionType + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", userId=" + userId +
                '}';
    }
}
