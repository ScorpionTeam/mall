package com.scoprion.mall.domain;


import com.alibaba.fastjson.annotation.JSONField;
import java.util.Date;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
public class Banner {

    /**
     * 主键
     */
    private Long id;

    /**
     * 轮播图名称
     */
    private String name;

    /**
     * 轮播图图片地址
     */
    @JSONField(name = "image_url")
    private String imageUrl;

    /**
     * 跳转链接
     */
    @JSONField(name = "action_url")
    private String actionUrl;

    /**
     * 访问量
     */
    @JSONField(name = "visit_total")
    private int visitTotal;

    /**
     * 状态 0 NORMAL 正常 1UN_NORMAL 删除
     */
    private String status;
    /**
     * 显示顺序，值越大越靠前
     */
    private int sort;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "update_date")
    private Date updateDate;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

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

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public int getVisitTotal() {
        return visitTotal;
    }

    public void setVisitTotal(int visitTotal) {
        this.visitTotal = visitTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", actionUrl='" + actionUrl + '\'' +
                ", visitTotal=" + visitTotal +
                ", status='" + status + '\'' +
                ", sort=" + sort +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
