package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <商户>
 * @date 2017-12-07 10:36
 */
public class Store {

    private Long id;

    /**
     * 店铺审核状态 AUDITING 待审核/审核中    NOT_PASS_AUDIT审核未通过  PASS_AUDIT审核通过
     */
    private String audit;

    /**
     * 店铺状态   NORMAL 正常   UN_NORMAL关闭  STATUS_DELETE 删除状态
     */
    private String status;


    /**
     * 电话
     */
    private String mobile;


    /**
     * 用户id
     */
    @JSONField(name = "user_id")
    private Long userId;

    /**
     * 微信id
     */
    @JSONField(name = "wx_code")
    private String wxCode;

    /**
     * 信誉值 0-99
     */
    private Integer reputation;

    /**
     * 店铺编号
     */
    @JSONField(name = "store_no")
    private String storeNo;

    /**
     * 店铺名称
     */
    @JSONField(name = "store_name")
    private String storeName;

    /**
     * 店铺地址
     */
    @JSONField(name = "store_address")
    private String storeAddress;

    /**
     * 店铺描述
     */
    @JSONField(name = "store_description")
    private String storeDescription;

    /**
     * 修改时间
     */
    @JSONField(name = "update_date", format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 创建时间
     */
    @JSONField(name = "create_date", format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 不通过的原因
     */
    private String reason;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", audit='" + audit + '\'' +
                ", status='" + status + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userId=" + userId +
                ", wxCode='" + wxCode + '\'' +
                ", reputation=" + reputation +
                ", storeNo='" + storeNo + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", storeDescription='" + storeDescription + '\'' +
                ", updateDate=" + updateDate +
                ", createDate=" + createDate +
                ", reason='" + reason + '\'' +
                '}';
    }
}
