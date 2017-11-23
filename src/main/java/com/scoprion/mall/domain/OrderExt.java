package com.scoprion.mall.domain;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */
public class OrderExt extends Order {


    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 微信用户标识code
     */
    private String openCode;

    /**
     * 发货信息
     */
    private SendGood sendGood;

    /**
     * 收件人信息
     */
    private Delivery delivery;


    /**
     * 商品图片
     */
    private String goodMainImage;
    /**
     * 快递公司名称
     */
    private String expressName;

    /**
     * 微信code
     */
    private String wxCode;
    /**
     * 商品编号
     */
    private String goodNo;

    /**
     * 活动商品id
     */
    private Long activityGoodId;

    public OrderExt() {

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
                ", delivery=" + delivery +
                ", goodMainImage='" + goodMainImage + '\'' +
                ", expressName='" + expressName + '\'' +
                ", wxCode='" + wxCode + '\'' +
                ", activityGoodId=" + activityGoodId +
                '}';
    }
}
