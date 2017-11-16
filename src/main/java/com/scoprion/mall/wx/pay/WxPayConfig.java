package com.scoprion.mall.wx.pay;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class WxPayConfig {

    public final static String APP_ID = "wx5e495b0ad7d9ac5d";

    public final static String APP_SECRET = "6eb633a285acdb7b44932cacb6a9a5f2";

    public final static String MCH_SECRET = "rzxlszyykpbgqcflzxsqcysyhljttclb";
    /**
     * 证书
     */
    public static String CERT_SECRET = System.getProperty("user.dir")
            + System.getProperty("file.separator") + "cert" + System.getProperty(
            "file.separator") + "apiclient_cert.p12";
    /**
     * 商户号
     */
    public final static String MCHID = "1491404382"; //

    public final static String OPEN_ID_URL = "https://api.weixin.qq.com/sns/jscode2session?";

    /**
     * 退款
     */
    public final static String WECHAT_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 微信商户平台支付结果通知URL
     * 线上环境
     */
    public final static String NOTIFY_URL = "http://2cjhzf.natappfree.cc/mall/wx/jsapi/callback/pay";
    /**
     * 统一下单URL
     */
    public final static String WECHAT_UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 查询订单URL
     */
    public final static String WECHAT_ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 获取token接口(GET)
     */
    public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * ticket 接口 (GET)
     */
    public final static String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    /**
     * oauth2授权接口(GET)
     */
    public final static String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * ==================================访问分析======================================================
     */

    /**
     * 访问趋势
     */
    public final static String VISIT_REND="https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend?access_token="+APP_SECRET;

}
