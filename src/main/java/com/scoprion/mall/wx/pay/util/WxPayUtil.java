package com.scoprion.mall.wx.pay.util;

import com.scoprion.mall.wx.pay.domain.OrderQueryRequestData;
import com.scoprion.mall.wx.pay.domain.OrderQueryResponseData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderNotifyRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderRequestData;
import com.scoprion.mall.wx.pay.domain.UnifiedOrderResponseData;
import com.scoprion.mall.wx.pay.WxPayConfig;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

//    /**
//     * 利用反射获取Java对象的字段并进行加密，过滤掉sign字段
//     *
//     * @param data
//     * @return return:String
//     */
//    public static String getSign(Object data) {
//        StringBuilder stringA = new StringBuilder();
//        Map<String, String> map = new HashMap<String, String>();
//        Field[] fields = data.getClass().getDeclaredFields();
//        Method[] methods = data.getClass().getDeclaredMethods();
//        for (Field field : fields) {
//            String fieldName = field.getName();
//            if (field != null && !fieldName.equals("sign")) {
//                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//                for (Method method : methods) {
//                    if (method.getName().equals(getMethodName)) {
//                        try {
//                            if (method.invoke(data, new Object[]{}) != null && method.invoke(data, new Object[]{})
//                                    .toString()
//                                    .length() != 0) {
//                                map.put(fieldName, method.invoke(data, new Object[]{}).toString());
//                            }
//                        } catch (IllegalAccessException | IllegalArgumentException
//                                | InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        List<Map.Entry<String, String>> mappingList = null;
//        //通过ArrayList构造函数把map.entrySet()转换成list
//        mappingList = new ArrayList<Map.Entry<String, String>>(map.entrySet());
//        //通过比较器实现比较排序
//        Collections.sort(
//                mappingList,
//                new Comparator<Map.Entry<String, String>>() {
//                    public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
//                        return mapping1.getKey().compareTo(mapping2.getKey());
//                    }
//                }
//        );
//        for (Map.Entry<String, String> mapping : mappingList) {
//            stringA.append("&").append(mapping.getKey()).append("=").append(mapping.getValue());
//        }
//        String stringSignTemp = stringA.append("&key=").append(WxPayConfig.KEY).substring(1);
//        return WxUtil.MD5(stringSignTemp).toUpperCase();
//    }

    /**
     * map转 xml
     *
     * @param map
     * @return
     */
    public static String MapConvertToXML(Map<String, Object> map) {
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
        stringBuffer.append("&key="+WxPayConfig.MCH_SECRET);
        return stringBuffer.toString();
    }


}
