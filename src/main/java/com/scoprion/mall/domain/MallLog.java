package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created on 2017/10/14.
 */
public class MallLog {

    //主键
    private Long id;

    //商品编码
    @JSONField(name = "good_no")
    private String goodNo;

    //店铺编码
    @JSONField(name = "seller_no")
    private String sellerNo;

    //日志类型
    //1 浏览
    //2 下单
    //3 支付
    //4 商品扣减
    //5 商品快照
    private String type;

    //ip地址
    @JSONField(name = "ip_address")
    private String ipAddress;

    //操作mi
    private String description;

    //创建时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MallLog{" +
                "id=" + id +
                ", goodNo='" + goodNo + '\'' +
                ", sellerNo='" + sellerNo + '\'' +
                ", type='" + type + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
