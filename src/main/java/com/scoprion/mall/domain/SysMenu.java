package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-21 16:19
 */
public class SysMenu {

    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单名称
     */
    @JSONField(name = "menu_name")
    private String menuName;

    /**
     * 父节点ID
     */
    private Long pid;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 菜单类型 0 父级 1 子级
     */
    @JSONField(name = "menu_type")
    private String menuType;

    /**
     * 状态 0 启用 1 停用
     */
    private String status;

    /**
     * 创建时间
     */
    @JSONField(name = "create_date", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 修改时间
     */
    @JSONField(name = "update_date", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单排序
     */
    private Integer sort;

    @Transient
    private List<SysMenu> leaf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SysMenu> getLeaf() {
        return leaf;
    }

    public void setLeaf(List<SysMenu> leaf) {
        this.leaf = leaf;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", pid=" + pid +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", menuType='" + menuType + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", operator='" + operator + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", leaf=" + leaf +
                '}';
    }
}
