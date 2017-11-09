package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <商城图片绑定关系实体>
 * @date 2017-11-08 10:13
 */
public class MallImage {

    /**
     * 主键
     */
    private Long id;
    /**
     * 商品ID，
     */
    private Long targetId;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 商品图片地址
     */
    private String url;

    public MallImage() {
    }

    public MallImage(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MallImage{" +
                "id=" + id +
                ", targetId=" + targetId +
                ", createDate=" + createDate +
                ", url='" + url + '\'' +
                '}';
    }
}
