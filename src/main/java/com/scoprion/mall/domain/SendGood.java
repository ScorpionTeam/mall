package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author ycj
 * @version V1.0 <订单发货信息>
 * @date 2017-11-16 11:45
 */
public class SendGood {

    /**
     * 主键
     */
    private Long id;
    /**
     * 订单id
     */
    @JSONField(name = "order_id")
    private Long orderId;
    /**
     * 寄件人信息id
     */
    @JSONField(name = "sender_id")
    private Long senderId;
    /**
     * 快递名称
     */
    @JSONField(name = "express_name")
    private String expressName;
    /**
     * 快递公司编号
     */
    @JSONField(name = "express_no")
    private String expressNo;
    /**
     * 运单号
     */
    @JSONField(name = "delivery_no")
    private String deliveryNo;
    /**
     * 创建时间
     */
    @JSONField(name = "create_date",format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 修改时间
     */
    @JSONField(name = "create_date",format = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
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

    @Override
    public String toString() {
        return "OrderDeliverGood{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", senderId=" + senderId +
                ", expressName='" + expressName + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", deliveryNo='" + deliveryNo + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
