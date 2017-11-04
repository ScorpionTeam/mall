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

    }

    private static String castDataToXMLString(Object object) {
        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        return xStream.toXML(object);
    }
}
