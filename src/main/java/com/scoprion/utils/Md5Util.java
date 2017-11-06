package com.scoprion.utils;

import java.security.MessageDigest;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class Md5Util {

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append(byteToHexString(bytes[i]));
        }
        return stringBuffer.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigist[d1] + hexDigist[d2];
    }

    public static String MD5Encode(String origin, String charsetName) {
        String result = null;
        try {
            result = new String(origin);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".equals(charsetName)) {
                result = byteArrayToHexString(messageDigest.digest(result.getBytes()));
            } else {
                result = byteArrayToHexString(messageDigest.digest(result.getBytes(charsetName)));
            }
        } catch (Exception e) {

        }
        return result;
    }

    private static final String hexDigist[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
