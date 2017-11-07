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
    public static final String SALE_STATUS = "01";


    /**
     * 图片路径
     */
    private static final String BASE_IMG_PATH = "D:\\Downloads\\Mall\\";
    /**
     * 商品图片路径 0
     */
    public static final String GOODS_IMG_PATH = BASE_IMG_PATH + "/GoodsImage/";

    /**
     * 品牌图片路径1
     */
    public static final String BRAND_IMG_PATH = BASE_IMG_PATH + "/BrandImage/";
    /**
     * 文章图片路径2
     */
    public static final String ARTICLE_IMG_PATH = BASE_IMG_PATH + "/ArticleImage/";
    /**
     * 商品评价图片路径3
     */
    public static final String GOODS_REVIEWS_IMG_PATH = BASE_IMG_PATH + "/GoodsReviewsImage/";
    /**
     * 广告图片路径4
     */
    public static final String BANNER_IMG_PATH = BASE_IMG_PATH + "/BannerImage/";
    /**
     * 活动图片路径5
     */
    public static final String ACIVITY_IMG_PATH = BASE_IMG_PATH + "/ActivityImage/";
    /**
     * 其他模块图片路径6
     */
    public static final String OTHER_IMG_PATH = BASE_IMG_PATH + "/OtherImage/";
    /**
     * 是否裁剪0否 1是
     */
    public static final String CUT_TRUE = "1";

    /**
     * 是否加水印0否 1是
     */
    public static final String WATER_REMARK_TRUE = "1";
}
