package com.scoprion.mall.home.domain;

import java.util.Date;

/**
 * Created on 2017/9/26.
 */
public class User {

    //主键
    private Long id;

    //姓名
    private String name;

    //头像
    private String headPic;

    //昵称
    private String nickName;

    //性别 1 男 0 女
    private String sex;

    //年龄
    private int age;

    //身份证号码
    private String certificateId;

    //手机号码
    private String mobile;

    //联系地址
    private String address;

    //邮箱
    private String email;

    //实名认证  0 未认证  1 已认证未通过  2认证通过
    private String certification;

    //生日
    private Date bornDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headPic='" + headPic + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", certificateId='" + certificateId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", certification='" + certification + '\'' +
                ", bornDate=" + bornDate +
                '}';
    }
}
