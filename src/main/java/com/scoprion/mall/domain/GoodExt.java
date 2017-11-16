package com.scoprion.mall.domain;

import java.util.List;

/**
 * @author admin1
 * @date 2017/11/1
 */
public class GoodExt extends Goods {

    /**
     * 活动Id
     */
    private Long activityId;


    /**
     * 活动类型
     * 0秒杀
     * 1拼团
     * 2优选
     */
    private String activityType;

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
    private Long goodId;


    /**
     * 商户名称
     */
    private Long sellerName;


    /**
     * 图片地址暂定最多4-5张
     */
    private List<MallImage> imgList;

    /**
     * 商品状态 0正常，1删除
     */
    private String status;

    /**
     * 商品规格列表
     */
    private List<GoodAttrExt> goodAttrExts;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
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

    public List<GoodAttrExt> getGoodAttrExts() {
        return goodAttrExts;
    }

    public void setGoodAttrExts(List<GoodAttrExt> goodAttrExts) {
        this.goodAttrExts = goodAttrExts;
    }

    @Override
    public String toString() {
        return "GoodExt{" +
                "activityId=" + activityId +
                ", activityType='" + activityType + '\'' +
                ", target='" + target + '\'' +
                ", goodId=" + goodId +
                ", sellerName=" + sellerName +
                ", imgList=" + imgList +
                ", status='" + status + '\'' +
                ", goodAttrExts=" + goodAttrExts +
                '}';
    }
}
