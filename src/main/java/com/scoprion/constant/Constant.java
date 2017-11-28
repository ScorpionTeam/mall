package com.scoprion.constant;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class Constant {
    /**
     * 手机号码长度
     */
    public static final int MOBILE_LENGTH = 11;

    /**
     * 密码最小长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 6;


    /**
     * 图片路径
     */
//    public static final String BASE_IMG_DIR = "D:\\Downloads\\";
    public static final String BASE_IMG_DIR = "/home/hcon/downloads";
    /**
     * 商品图片路径 0
     */
    public static final String GOODS_IMG_PATH = BASE_IMG_DIR + "/mall/goodimage/";

    /**
     * 品牌图片路径 1
     */
    public static final String BRAND_IMG_PATH = BASE_IMG_DIR + "/mall/brandimage/";
    /**
     * 文章图片路径 2
     */
    public static final String ARTICLE_IMG_PATH = BASE_IMG_DIR + "/mall/articleimage/";
    /**
     * 商品评价图片路径 3
     */
    public static final String GOODS_ESTIMATE_IMG_PATH = BASE_IMG_DIR + "/mall/goodsestimateimage/";
    /**
     * 广告图片路径 4
     */
    public static final String BANNER_IMG_PATH = BASE_IMG_DIR + "/mall/bannerimage/";
    /**
     * 活动图片路径 5
     */
    public static final String ACTIVITY_IMG_PATH = BASE_IMG_DIR + "/mall/activityimage/";
    /**
     * 其他模块图片路径6
     */
    public static final String OTHER_IMG_PATH = BASE_IMG_DIR + "/mall/otherimage/";

    /**
     * 水印图片地址 绝对路径
     */
    public static final String WATER_REMARK_IMAGE_PATH = BASE_IMG_DIR + "/mall/mall-waterremark.png";

    /**
     * idList参数
     */
    public static final String ID_LIST = "idList";
    /**
     * status参数
     */
    public static final String STATUS = "status";

    /**
     * 图片尺寸集合
     */
    public static final String[] SIZE_ARR = {"30x30", "40x40", "60x60", "220x220", "400x400"};
    /**
     * 添加水印的图片尺寸不能小于这个值
     */
    public static final int WATER_REMARK_SIZE = 220;

    /**
     * 微信订单查询返回--支付成功
     */
    public static final String WX_PAY_SUCCESS = "SUCCESS";

    /**
     * 个人积分等级划分
     */
    public static final int WX_POINT_LEVEL1 = 36;
    public static final int WX_POINT_LEVEL2 = 750;
    public static final int WX_POINT_LEVEL3 = 1500;

    public static final long TIME_TWO_HOUR = 1000 * 60 * 60 * 2;
}
