package com.scoprion.mall.domain.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.scoprion.mall.domain.Delivery;
import com.scoprion.mall.domain.SendGood;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */
public class OrderExt extends Order {


    /**
     * IP地址
     */
    @JSONField(name = "ip_address")
    private String ipAddress;

    /**
     * 微信用户标识code
     */
    @JSONField(name = "open_code")
    private String openCode;

    /**
     * 发货信息
     */
    @JSONField(name = "send_good")
    private SendGood sendGood;

    /**
     * 收件人信息
     */
    private Delivery delivery;


    /**
     * 商品图片
     */
    @JSONField(name = "good_main_image")
    private String goodMainImage;
    /**
     * 快递公司名称
     */
    @JSONField(name = "express_name")
    private String expressName;

    /**
     * 微信code
     */
    @JSONField(name = "wx_code")
    private String wxCode;
    /**
     * 商品编号
     */
    @JSONField(name = "good_no")
    private String goodNo;

    /**
     * 活动商品id
     */
    @JSONField(name = "activity_good_id")
    private Long activityGoodId;

    public OrderExt() {

    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getGoodMainImage() {
        return goodMainImage;
    }

    public void setGoodMainImage(String goodMainImage) {
        this.goodMainImage = goodMainImage;
    }

    public OrderExt(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public SendGood getSendGood() {
        return sendGood;
    }

    public void setSendGood(SendGood sendGood) {
        this.sendGood = sendGood;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public Long getActivityGoodId() {
        return activityGoodId;
    }

    public void setActivityGoodId(Long activityGoodId) {
        this.activityGoodId = activityGoodId;
    }

    @Override
    public String toString() {
        return "OrderExt{" +
                "ipAddress='" + ipAddress + '\'' +
                ", openCode='" + openCode + '\'' +
                ", sendGood=" + sendGood +
                ", DeliveryService=" + delivery +
                ", goodMainImage='" + goodMainImage + '\'' +
                ", expressName='" + expressName + '\'' +
                ", wxCode='" + wxCode + '\'' +
                ", activityGoodId=" + activityGoodId +
                '}';
    }
}
