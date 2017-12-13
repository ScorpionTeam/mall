package com.scoprion.mall.wx.pay.util;

import com.scoprion.mall.wx.pay.domain.OrderQueryRequestData;
import com.scoprion.mall.wx.pay.domain.OrderQueryResponseData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.scoprion.mall.wx.pay.domain.WxRefundNotifyResponseData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class WxPayUtil {

    public static UnifiedOrderResponseData unifieOrder(UnifiedOrderRequestData requestData) {
        String requestXMLData = WxPayUtil.castDataToXMLString(requestData);
        String requertUrl = WxPayConfig.WECHAT_ORDER_QUERY_URL;
        String method = "POST";
        String responseString = WxUtil.httpsRequest(requertUrl, method, requestXMLData);
        UnifiedOrderResponseData responseData = WxPayUtil.castXMLStringToUnifiedOrderResponseData(responseString);
        return responseData;
    }

    /**
     * 调用订单查询接口
     *
     * @param data
     * @return return:OrderQueryResponseData
     */
    public static OrderQueryResponseData queryOrder(OrderQueryRequestData data) {
        String requestXMLData = WxPayUtil.castDataToXMLString(data);
        String requestUrl = WxPayConfig.WECHAT_ORDER_QUERY_URL;
        String requestMethod = "POST";
        String responseString = WxUtil.httpsRequest(requestUrl, requestMethod, requestXMLData);
        OrderQueryResponseData responseData = WxPayUtil.castXMLStringToOrderQueryResponseData(responseString);
        return responseData;
    }

    public static String castDataToXMLString(Object object) {
        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        return xStream.toXML(object);
    }

    /**
     * 把XML字符串转换为统一下单接口返回数据对象
     *
     * @param responseString
     * @return return:UnifiedOrderResponseData
     */
    public static UnifiedOrderResponseData castXMLStringToUnifiedOrderResponseData(String responseString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", UnifiedOrderResponseData.class);
        xStream.processAnnotations(UnifiedOrderResponseData.class);
        return (UnifiedOrderResponseData) xStream.fromXML(responseString);
    }

    /**
     * 把XML字符串转换为退款返回对象
     *
     * @param responseString
     * @return
     */
    public static WxRefundNotifyResponseData castXMLStringToWxRefundNotifyResponseData(String responseString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", WxRefundNotifyResponseData.class);
        xStream.processAnnotations(WxRefundNotifyResponseData.class);
        return (WxRefundNotifyResponseData) xStream.fromXML(responseString);
    }

    /**
     * 把XML字符串转换为统一下单回调接口请求数据对象
     *
     * @param requestString
     * @return return:UnifiedOrderNotifyRequestData
     */
    public static UnifiedOrderNotifyRequestData castXMLStringToUnifiedOrderNotifyRequestData(String requestString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", UnifiedOrderNotifyRequestData.class);
        return (UnifiedOrderNotifyRequestData) xStream.fromXML(requestString);
    }

    /**
     * 把XML字符串转换为订单查询接口返回数据对象
     *
     * @param responseString
     * @return return:OrderQueryResponseData
     */
    public static OrderQueryResponseData castXMLStringToOrderQueryResponseData(String responseString) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", OrderQueryResponseData.class);
        return (OrderQueryResponseData) xStream.fromXML(responseString);
    }

    /**
     * map转 xml
     *
     * @param map
     * @return
     */
    public static String mapConvertToXML(Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<xml>");
        for (String key : map.keySet()) {
            stringBuffer.append("<" + key + ">" + map.get(key) + "</" + key + ">");
        }
        stringBuffer.append("</xml>");
        return stringBuffer.toString();
    }

    /**
     * 签名排序
     *
     * @param map
     * @return
     */
    public static String sort(Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        Collection collection = map.keySet();
        List list = new ArrayList<String>(collection);
        Collections.sort(list);
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (j == 0) {
                stringBuffer.append(list.get(i) + "=" + map.get(list.get(i)));
            } else {
                stringBuffer.append("&" + list.get(i) + "=" + map.get(list.get(i)));
            }
            j++;
        }
        stringBuffer.append("&key=" + WxPayConfig.MCH_SECRET);
        return stringBuffer.toString();
    }

    /**
     * 退款操作
     *
     * @param url
     * @param data
     * @param partner
     * @return
     */
    public static String doRefund(String url, String data, String partner) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream inputStream = new FileInputStream(new File(WxPayConfig.CERT_SECRET));
        try {
            keyStore.load(inputStream, partner.toCharArray());
        } finally {
            inputStream.close();
        }
        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, partner.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Connection", "keep-alive");
            httpPost.addHeader("Accept", "*/*");
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.addHeader("Host", "api.mch.weixin.qq.com");
            httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpPost.addHeader("Cache-Control", "max-age=0");
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpPost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }


    /**
     * 支付签名
     *
     * @param map
     * @return
     */
    public static String paySign(Map<String, Object> map) {
        return WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
    }

    /**
     * 生成支付参数
     *
     * @param timeStamp
     * @param nonceStr
     * @param prepayId
     * @return
     */
    public static Map<String, Object> payParam(Long timeStamp, String nonceStr, String prepayId) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("appId", WxPayConfig.APP_ID);
        map.put("nonceStr", nonceStr);
        map.put("package", "prepay_id=" + prepayId);
        map.put("signType", "MD5");
        map.put("timeStamp", timeStamp.toString());
        return map;
    }


    /**
     * 统一下单参数
     *
     * @param body       商品描述
     * @param openid     用户openid
     * @param outTradeNo 商户订单号
     * @return
     */
    public static String unifiedOrder(String body,
                                      String openid,
                                      String outTradeNo,
                                      int paymentFee,
                                      String nonceStr) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("openid", openid);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonceStr);
        map.put("body", body);
        map.put("out_trade_no", outTradeNo);
        map.put("total_fee", paymentFee);
        map.put("notify_url", WxPayConfig.NOTIFY_URL);
        map.put("trade_type", "JSAPI");
        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        return WxPayUtil.mapConvertToXML(map);
    }

    /**
     * 拼团下单参数
     *
     * @param body       商品描述
     * @param openid     用户openid
     * @param outTradeNo 商户订单号
     * @return
     */
    public static String placeOrder(String body,
                                    String openid,
                                    String outTradeNo,
                                    int paymentFee,
                                    String nonceStr) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("openid", openid);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonceStr);
        map.put("body", body);
        map.put("out_trade_no", outTradeNo);
        map.put("total_fee", paymentFee);
        map.put("notify_url", WxPayConfig.NOTIFY_URL_GROUP);
        map.put("trade_type", "JSAPI");
        System.out.println("排序参数:" + WxPayUtil.sort(map));

        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        System.out.println("传输的参数:" + WxPayUtil.mapConvertToXML(map));
        return WxPayUtil.mapConvertToXML(map);
    }

    /**
     * 免费试用下单参数
     *
     * @param body       商品描述
     * @param userId     用户userId
     * @param outTradeNo 商户订单号
     * @return
     */
    public static String freeOrder(String body,
                                    String userId,
                                    String outTradeNo,
                                    int paymentFee,
                                    String nonceStr) {

        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("openid", userId);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonceStr);
        map.put("body", body);
        map.put("out_trade_no", outTradeNo);
        map.put("total_fee", paymentFee);
        map.put("notify_url", WxPayConfig.FREE_URL_GROUP);
        map.put("trade_type", "JSAPI");
        System.out.println("排序参数:" + WxPayUtil.sort(map));

        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        System.out.println("传输的参数:" + WxPayUtil.mapConvertToXML(map));
        return WxPayUtil.mapConvertToXML(map);
    }

    /**
     * 退款读取证书
     *
     * @param orderNo
     * @param totalFee
     * @param refundFee
     * @return
     */
    public static String refundSign(
            String orderNo,
            int totalFee,
            int refundFee,
            String refundNo,
            String nonceStr) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("appid", WxPayConfig.APP_ID);
        map.put("mch_id", WxPayConfig.MCHID);
        map.put("nonce_str", nonceStr);
        map.put("out_trade_no", orderNo);
        map.put("out_refund_no", refundNo);
        map.put("total_fee", totalFee);
        map.put("refund_fee", refundFee);
        map.put("op_user_id", "100000");
        String sign = WxUtil.MD5(WxPayUtil.sort(map)).toUpperCase();
        map.put("sign", sign);
        return WxPayUtil.mapConvertToXML(map);
    }
}
