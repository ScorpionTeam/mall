package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
public class Brand {

    /**
     * 主键
     */
    private Long id;

    /**
     * 品牌名称
     */
    @JSONField(name = "brand_name")
    private String brandName;

    /**
     * 品牌简称
     */
    @JSONField(name = "short_name")
    private String shortName;

    /**
     * 品牌所属国家
     */
    private String country;

    /**
     * 品牌状态 1入驻  0 退出 2 删除
     */
    private String status;

    /**
     * 品牌负责人
     */
    private String contact;

    /**
     * 负责人电话
     */
    @JSONField(name = "contact_phone")
    private String contactPhone;

    /**
     * 品牌图片
     */
    @JSONField(name = "brand_image")
    private String brandImage;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", country='" + country + '\'' +
                ", status='" + status + '\'' +
                ", contact='" + contact + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", brandImage='" + brandImage + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
