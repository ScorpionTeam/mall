package com.scoprion.mall.domain.good;

import com.alibaba.fastjson.annotation.JSONField;
import com.scoprion.mall.domain.MallImage;

import java.util.Date;
import java.util.List;

/**
 * @author admin1
 * @date 2017/11/1
 */
public class GoodExt extends Goods {

    /**
     * 活动Id
     */
    @JSONField(name = "activity_id")
    private Long activityId;

    /**
     * 活动开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "start_date")
    private Date startDate;

    /**
     * 活动结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss",name = "end_date")
    private Date endDate;

    /**
     * 活动类型
     * 0秒杀
     * 1拼团
     * 2优选
     */
    @JSONField(name = "activity_type")
    private String activityType;

    /**
     * 活动状态 状态 0正常 1删除
     */
    @JSONField(name = "activity_status")
    private String activityStatus;

    @JSONField(name = "activity_stock")
    private int activityStock;

    /**
     * 目标
     * 0 小程序
     * 1 app
     * 2 网站
     */
    private String target;

    /**
     * 商品Id
     */
    @JSONField(name = "good_id")
    private Long goodId;


    /**
     * 商户名称
     */
    @JSONField(name = "seller_name")
    private Long sellerName;


    /**
     * 图片地址暂定最多4-5张
     */
    private List<MallImage> imgList;

    public int getActivityStock() {
        return activityStock;
    }

    public void setActivityStock(int activityStock) {
        this.activityStock = activityStock;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Long getSellerName() {
        return sellerName;
    }

    public void setSellerName(Long sellerName) {
        this.sellerName = sellerName;
    }

    public List<MallImage> getImgList() {
        return imgList;
    }

    public void setImgList(List<MallImage> imgList) {
        this.imgList = imgList;
    }

}
