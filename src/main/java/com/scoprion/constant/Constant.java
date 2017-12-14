package com.scoprion.constant;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class Constant {

    public static final String  ENCRYPT_KEY="ScorpionMall8888";
    /**
     * 手机号码长度
     */
    public static final int MOBILE_LENGTH = 11;

    /**
     * 密码最小长度限制
     */
    public static final int PASSWORD_MIN_LENGTH = 6;

    /**
     * 邮箱判断正则表达式
     */
    public static final String REGEX = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
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
    public static final String GOODS_ESTIMATE_IMG_PATH = GOODS_IMG_PATH + "estimateimage/";
    /**
     * 广告图片路径 4
     */
    public static final String BANNER_IMG_PATH = BASE_IMG_DIR + "/mall/bannerimage/";
    /**
     * 活动图片路径 5
     */
    public static final String ACTIVITY_IMG_PATH = BASE_IMG_DIR + "/mall/activityimage/";


    /**
     * 富文本图片路径 6
     */
    public static final String RICH_TEXT_IMG_PATH = GOODS_IMG_PATH + "richtext/";

    /**
     * 证件照图片路径  7
     */
    public static final String ID_CARD_IMG_PATH = BASE_IMG_DIR + "/mall/idcardimage/";

    /**
     * 其他模块图片路径
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

    /**
     * 拼团支付时间30分钟
     */
    public static final long TIME_HALF_HOUR = 1000 * 60 * 30;

    public static final int ACTIVITY_NUMBER = 5;

    //线程数
    public static final int THREAD_COUNT = 5;

    //处理间隔时间  毫秒
    public static final int INTERVAL_MILLS = 0;

    //失败后等待时间 毫秒
    public static final int ONE_SECOND = 1 * 1000;

    //异常休眠时间
    public static final int ONE_MINUTE = 1 * 60 * 1000;

    //MQ 消息重试时间
    public static final int RETRY_TIME_INTERVAL = ONE_MINUTE;

    //MQ 消息有效时间
    public static final int VALID_TIME = ONE_MINUTE;

    public static final String QUEUE = "mall.queue.content";
    public static final String EXCHANGE = "mall.exchange.content";
    public static final String ROUTING_KEY = "mall.routingkey.content";

    public static final String REFUND_QUEUE = "mall.queue.refund";
    public static final String REFUND_EXCHANGE = "mall.exchange.refund";
    public static final String REFUND_ROUTING_KEY = "mall.routingkey.refund";

}
