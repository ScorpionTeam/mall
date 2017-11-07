package com.scoprion.mall.wx.pay;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class WxPayConfig {

    /**
     * 小程序id
     */
    public final static String APPID = "";
    /**
     * 小程序secret
     */
    public final static String APPSECRET = "";


    public final static String MCH_SECRET="";
    /**
     * 证书
     */
    public static String CERT_FILE = System.getProperty("user.dir")
            + System.getProperty("file.separator") + "fscert" + System.getProperty(
            "file.separator") + "apiclient_cert.p12";
    /**
     * 商户号
     */
    public final static String MCHID = "1491404382"; //
    /**
     * 公众平台商户KEY
     */
    public final static String KEY = "rzxlszyykpbgqcflzxsqcysyhljttclb";

    public final static String OPEN_ID_URL="https://api.weixin.qq.com/sns/jscode2session?";
    /**
     * 微信商户平台支付结果通知URL
     * 线上环境
     */
    public final static String NOTIFY_URL = "https://localhost:8088/mall/wx/jsapi/callback/pay";
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
}
