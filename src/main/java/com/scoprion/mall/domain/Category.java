package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author by kunlun
 * @created on 2017/9/28.
 */
public class Category {

    /**
     * 类目id
     */
    private Long id;

    /**
     * 类目名称
     */
    @JSONField(name = "category_name")
    private String categoryName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "update_date")
    private Date updateDate;

    /**
     * 父id
     */
    @JSONField(name = "parent_id")
    private Long parentId;

    /**
     * 跳转链接
     */
    @JSONField(name = "action_url")
    private String actionUrl;
    /**
     * 排序 由小到大 越小越往前
     */
    private Integer sort;
    /**
     * 图标（一级类目才有）
     */
    private String icon;

    /**
     * 状态 NORMAL UN_NORMAL
     */
    private String status;

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", parentId=" + parentId +
                ", actionUrl='" + actionUrl + '\'' +
                ", sort='" + sort + '\'' +
                ", icon='" + icon + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
