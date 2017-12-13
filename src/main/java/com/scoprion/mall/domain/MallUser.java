package com.scoprion.mall.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created on 2017/9/26.
 */
public class MallUser {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    @JSONField(name = "head_picture")
    private String headPic;

    /**
     * 昵称
     */
    @JSONField(name = "nick_name")
    private String nickName;

    /**
     * 性别   MALE("MALE", "男"),FEMALE("FEMALE", "女"),
     */
    private String sex;

    /**
     * 年龄
     */
    private int age;

    /**
     * 身份证号码
     */
    @JSONField(name = "certificate_id")
    private String certificateId;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 实名认证  实名认证 NOT_AUTH  未实名认证 ,AUDITING 审核中， IS_AUTH"  认证通过, NOT_PASS_AUTH 认证未通过
     */
    private String certification;

    /**
     * 生日
     */
    @JSONField(name = "born_date")
    private String bornDate;

    /**
     * 状态 NORMAL正常 UN_NORMAL已注销
     */
    private String status;

    /**
     * 城市编码
     */
    private int city;

    /**
     * 注册时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "update_date")
    private Date updateDate;

    /**
     * 最后一次登录时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "last_login_date")
    private Date lastLoginDate;

    /**
     * 登录ip
     */
    @JSONField(name = "login_ip")
    private String loginIp;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录token
     */
    private String token;

    /**
     * 类型 用户类型 USER_ADMIN 管理员，USER_ORDINARY 普通用户,SELLER 商户
     */
    @JSONField(name = "user_type")
    private String userType;

    /**
     * 证件照正面
     */
    @JSONField(name = "id_photo_front_url")
    private String idPhotoFrontUrl;

    /**
     * 证件照正反面
     */
    @JSONField(name = "id_photo_bg_url")
    private String idPhotoBgUrl;

    public MallUser() {
    }

    public MallUser(String nickName, String mobile, String password) {
        this.nickName = nickName;
        this.mobile = mobile;
        this.password = password;
    }

    public String getIdPhotoFrontUrl() {
        return idPhotoFrontUrl;
    }

    public void setIdPhotoFrontUrl(String idPhotoFrontUrl) {
        this.idPhotoFrontUrl = idPhotoFrontUrl;
    }

    public String getIdPhotoBgUrl() {
        return idPhotoBgUrl;
    }

    public void setIdPhotoBgUrl(String idPhotoBgUrl) {
        this.idPhotoBgUrl = idPhotoBgUrl;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Override
    public String toString() {
        return "Member{" +
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
                ", bornDate='" + bornDate + '\'' +
                ", status='" + status + '\'' +
                ", city=" + city +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", lastLoginDate=" + lastLoginDate +
                ", loginIp='" + loginIp + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
