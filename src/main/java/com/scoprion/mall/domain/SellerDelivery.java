package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author by kunlun
 * @created on 2017/11/28.
 */
public class SellerDelivery {

    /**
     * 主键
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String area;

    /**
     * 邮编
     */
    @JSONField(name = "post_code")
    private String postCode;

    /**
     * 默认地址
     */
    @JSONField(name = "default_address")
    private String defaultAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    @Override
    public String toString() {
        return "SellerDelivery{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", postCode='" + postCode + '\'' +
                ", defaultAddress='" + defaultAddress + '\'' +
                '}';
    }
}
