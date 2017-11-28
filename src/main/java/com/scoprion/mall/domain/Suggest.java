package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <意见建议、投诉>
 * @date 2017-11-17 10:07
 */
public class Suggest {
    /**
     * 主键
     */
    private Long id;

    /**
     * 类型（0UI,1购物体验，2售后服务，3投诉，4其他建议）
     */
    private String type;

    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */

    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    /**
     * 用户id
     */
    @JSONField(name = "user_id")
    private String userId;

    /**
     * 微信id
     */
    @JSONField(name = "wx_code")
    private String wxCode;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Suggest{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}
