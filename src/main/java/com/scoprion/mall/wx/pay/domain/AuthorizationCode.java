package com.scoprion.mall.wx.pay.domain;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class AuthorizationCode {

    private String session_key;

    private int expires_in;

    private String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "AuthorizationCode{" +
                "session_key='" + session_key + '\'' +
                ", expires_in=" + expires_in +
                ", openid='" + openid + '\'' +
                '}';
    }
}
