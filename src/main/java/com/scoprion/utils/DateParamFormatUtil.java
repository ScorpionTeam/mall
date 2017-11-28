package com.scoprion.utils;

import com.alibaba.druid.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-28 12:01
 */
public class DateParamFormatUtil {
    public static String formatDate(String targetDateString) {
        if (StringUtils.isEmpty(targetDateString)) {
            return null;
        }
        if (targetDateString.contains(" 0800 (中国标准时间)")) {
            targetDateString = targetDateString.replace(" 0800 (中国标准时间)", "+08:00");
        } else {
            return targetDateString;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss", Locale.ENGLISH);
        try {
            Date tmp2 = sdf.parse(targetDateString);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            targetDateString = sdf2.format(tmp2) + " 23:59:59";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetDateString;
    }
}
