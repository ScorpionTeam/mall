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
     * 上架状态
     */
    public static final String ON_SALE = "1";

    /**
     * 销售状态
     */
    public static final String STATUS_01 = "01";


    /**
     * 图片路径
     */
    public static final String BASE_IMG_DIR = "D:\\Downloads\\";
    /**
     * 商品图片路径 0
     */
    public static final String GOODS_IMG_PATH = BASE_IMG_DIR + "/Mall/GoodImage/";

    /**
     * 品牌图片路径 1
     */
    public static final String BRAND_IMG_PATH = BASE_IMG_DIR + "/Mall/BrandImage/";
    /**
     * 文章图片路径 2
     */
    public static final String ARTICLE_IMG_PATH = BASE_IMG_DIR + "/Mall/ArticleImage/";
    /**
     * 商品评价图片路径 3
     */
    public static final String GOODS_ESTIMATE_IMG_PATH = BASE_IMG_DIR + "/Mall/GoodsEstimateImage/";
    /**
     * 广告图片路径 4
     */
    public static final String BANNER_IMG_PATH = BASE_IMG_DIR + "/Mall/BannerImage/";
    /**
     * 活动图片路径 5
     */
    public static final String ACTIVITY_IMG_PATH = BASE_IMG_DIR + "/Mall/ActivityImage/";
    /**
     * 其他模块图片路径6
     */
    public static final String OTHER_IMG_PATH = BASE_IMG_DIR + "/Mall/OtherImage/";
    /**
     * 是否裁剪0否 1是
     */
    public static final String CUT_TRUE = "1";

    /**
     * 是否加水印0否 1是
     */
    public static final String WATER_REMARK_TRUE = "1";
    /**
     * 水印图片地址 绝对路径
     */
    public static final String WATER_REMARK_IMAGE_PATH = "D:\\Downloads\\logo_60x60.png";

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
     * 微信订单查询返回--转入退款
     */
    public static final String WX_PAY_REFUND = "REFUND";
    /**
     * 微信订单查询返回--未支付
     */
    public static final String WX_PAY_NOT_PAY = "NOTPAY";
    /**
     * 微信订单查询返回--已关闭
     */
    public static final String WX_PAY_CLOSED = "CLOSED";
    /**
     * 微信订单查询返回--已撤销（刷卡支付）
     */
    public static final String WX_PAY_REVOKED = "REVOKED";
    /**
     * 微信订单查询返回--用户支付中
     */
    public static final String WX_PAY_USER_PAYING = "USERPAYING";
    /**
     * 微信订单查询返回--支付失败(其他原因，如银行返回失败)
     */
    public static final String WX_PAY_PAY_ERROR = "PAYERROR";

    /**
     * 微信等级划分
     */
    public static final int WX_POINT_LEVEL1 = 36;
    public static final int WX_POINT_LEVEL2 = 750;
    public static final int WX_POINT_LEVEL3 = 1500;

    public static final String STATUS_ZERO = "0";
    public static final String STATUS_ONE = "1";

}
