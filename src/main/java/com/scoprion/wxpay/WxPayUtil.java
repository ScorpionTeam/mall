package com.scoprion.wxpay;

import com.scoprion.wxpay.domain.UnifiedOrderRequestData;
import com.scoprion.wxpay.domain.UnifiedOrderResponseData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class WxPayUtil {

    public static UnifiedOrderResponseData unifieOrder(UnifiedOrderRequestData requestData) {
        String requestXMLData = WxPayUtil.castDataToXMLString(requestData);
        String requertUrl = WxPayConfig.WECHAT_ORDER_QUERY_URL;
        String method ="POST";
        String responseString = WxUtil.httpsRequest(requertUrl,method,requestXMLData);
        UnifiedOrderResponseData responseData = WxPayUtil.castDataToXMLString()
        return null;
    }

    private static String castDataToXMLString(Object object) {
        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        return xStream.toXML(object);
    }

    /**
     * 把XML字符串转换为统一下单接口返回数据对象
     * @param responseString
     * @return
     * return:UnifiedOrderResponseData
     */
    private static UnifiedOrderResponseData castXMLStringToUnifiedOrderResponseData(String responseString){
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", UnifiedOrderResponseData.class);
        xStream.processAnnotations(UnifiedOrderResponseData.class);
        return (UnifiedOrderResponseData) xStream.fromXML(responseString);
    }

    /**
     * 把XML字符串转换为统一下单回调接口请求数据对象
     * @param requestString
     * @return
     * return:UnifiedOrderNotifyRequestData
     */
    public static UnifiedOrderNotifyRequestData castXMLStringToUnifiedOrderNotifyRequestData(String requestString){
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("xml", UnifiedOrderNotifyRequestData.class);
        return (UnifiedOrderNotifyRequestData) xStream.fromXML(requestString);
    }
}
