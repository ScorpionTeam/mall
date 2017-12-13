package com.scoprion.utils;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-12-13 14:08
 */
public class SellerNoUtil {
    public static String getSellerNo() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        String year = calendar.get(Calendar.YEAR) + "";
        String day = calendar.get(Calendar.DATE) + "";
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        String hour = calendar.get(Calendar.HOUR) + "";
        String minute = calendar.get(Calendar.MINUTE) + "";
        String seconds = calendar.get(Calendar.SECOND) + "";
        String sellerNo = year + month + day + "000" + hour + minute + seconds + get4Random();
        return sellerNo;
    }

    /**
     * 产生4位随机数
     *
     * @return
     */
    private static String get4Random() {
        Random random = new Random();
        String fourRandom = random.nextInt(100)+"";
        int len = fourRandom.length();
        if (len < 4) {
            for (int i = 0; i <= 4 - len; i++) {
                fourRandom = "0" + fourRandom;
            }
        }
        return fourRandom;
    }
}
