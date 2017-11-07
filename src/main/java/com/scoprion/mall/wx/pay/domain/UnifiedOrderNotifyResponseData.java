package com.scoprion.mall.wx.pay.domain;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class UnifiedOrderNotifyResponseData {


    private String return_code;

    private String return_msg;

    public UnifiedOrderNotifyResponseData(String return_code, String return_msg) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

    public UnifiedOrderNotifyResponseData() {

    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    @Override
    public String toString() {
        return "UnifiedOrderNotifyResponseData{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                '}';
    }
}
