package com.scoprion.result;

import com.alibaba.fastjson.JSONObject;

/**
 * Created on 2017/9/18.
 */
public class JSONResult {
    public static String fillResultString(Integer status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};

        return jsonObject.toString();
    }
}
