package com.scoprion.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 2017/9/16.
 */
public class IPUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPUtil.class);

    public static String getIPAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("ProxyAop-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-ProxyAop-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }


    /**
     * 获取真实ip地址  以及  归属地
     * @param request
     * @return
     */
    public static String getIpBelongAddress(HttpServletRequest request) {
        String ip = getIPAddress(request);
        String belongIp = getIpBelongAddress(ip);
        return ip + belongIp;
    }

    /**
     * 获取ip归属地
     * @param ip
     * @return
     */
    public static String getIpBelongAddress(String ip) {

        String ipAddress = "[]";
        try {
            String context = call("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            JSONObject jsonObject = JSONObject.parseObject(context);
            String code = jsonObject.getString("code");
            if (code.equals("0")) {
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                ipAddress = "[" + jsonObject1.get("country") + "/" + jsonObject1.get("city") + "]";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ipAddress;
    }

    public static String call(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == 200) {
                return streamConvertToString(conn.getInputStream());
            }

        } catch (Exception e) {
            LOGGER.error("获取ip所属地址出错", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将流转换为字符串
     * @param inputStream
     * @return
     */
    public static String streamConvertToString(InputStream inputStream) {
        String str = "";
        try {
            if (inputStream == null) {
                return null;
            }
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) != -1) {
                arrayOutputStream.write(bytes, 0, len);
            }
            str = new String(arrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }


}
