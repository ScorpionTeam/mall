package com.scoprion.mall.wx.pay.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 调用订单查询接口返回的数据
 *
 * @author by kunlun
 * @created on 2017/11/4.
 */
@XStreamAlias("xml")
public class OrderQueryResponseData {

    /**
     * 返回状态码
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    /**
     * appid
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 签名
     */
    private String sign;

    /**
     * 业务结果
     */
    private String result_code;

    /**
     * 错误代码
     */
    private String err_code;

    /**
     * 错误代码描述
     */
    private String err_code_des;

    //以下字段在return_code 和  result_code 都为SUCCESS的时候返回

    /**
     * 设备号
     */
    private String device_info;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 是否关注公众账号
     */
    private String is_subscribe;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 交易状态
     * SUCCESS -支付成功
     * REFUND  -转入退款
     * NOTPAY  -未支付
     * CLOSED  -已关闭
     * REVOKED -已撤销
     * USERPAYING - 用户支付中
     * PAYERROR  支付错误
     */
    private String trade_state;

    /**
     * 付款银行
     */
    private String bank_type;

    /**
     * 总金额
     */
    private int total_fee;

    /**
     * 货币种类
     */
    private String fee_type;

    /**
     * 现金支付金额
     */
    private String cash_fee;

    /**
     * 现金支付货币类型
     */
    private String cash_fee_type;

    /**
     * 代金券或立减优惠金额	coupon_fee	否	Int	100	“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
     */
    private String coupon_fee;
    //代金券或立减优惠使用数量	coupon_count	否	Int	1	代金券或立减优惠使用数量
    private String coupon_count;
    //代金券或立减优惠批次ID	coupon_batch_id_$n	否	String(20)	100	代金券或立减优惠批次ID ,$n为下标，从0开始编号
    private String coupon_batch_id_;
    //代金券或立减优惠ID	coupon_id_$n	否	String(20)	10000 	代金券或立减优惠ID, $n为下标，从0开始编号
    private String coupon_id_;
    //单个代金券或立减优惠支付金额	coupon_fee_$n	否	Int	100	单个代金券或立减优惠支付金额, $n为下标，从0开始编号
    private String coupon_fee_;
    //微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
    private String transaction_id;
    //商户订单号	out_trade_no	是	String(32)	1217752501201407033233368018	商户系统的订单号，与请求一致。
    private String out_trade_no;
    //商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
    private String attach;
    //支付完成时间	time_end	是	String(14)	20141030133525	订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    private String time_end;
    //交易状态描述	trade_state_desc	是	String(256)	支付失败，请重新下单支付	对当前查询订单状态的描述和下一步操作的指引
    private String trade_state_desc;

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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }


    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_batch_id_() {
        return coupon_batch_id_;
    }

    public void setCoupon_batch_id_(String coupon_batch_id_) {
        this.coupon_batch_id_ = coupon_batch_id_;
    }

    public String getCoupon_id_() {
        return coupon_id_;
    }

    public void setCoupon_id_(String coupon_id_) {
        this.coupon_id_ = coupon_id_;
    }

    public String getCoupon_fee_() {
        return coupon_fee_;
    }

    public void setCoupon_fee_(String coupon_fee_) {
        this.coupon_fee_ = coupon_fee_;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    @Override
    public String toString() {
        return "OrderQueryResponseData{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", device_info='" + device_info + '\'' +
                ", openid='" + openid + '\'' +
                ", is_subscribe='" + is_subscribe + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", trade_state='" + trade_state + '\'' +
                ", bank_type='" + bank_type + '\'' +
                ", total_fee=" + total_fee +
                ", fee_type='" + fee_type + '\'' +
                ", cash_fee='" + cash_fee + '\'' +
                ", cash_fee_type='" + cash_fee_type + '\'' +
                ", coupon_fee='" + coupon_fee + '\'' +
                ", coupon_count='" + coupon_count + '\'' +
                ", coupon_batch_id_='" + coupon_batch_id_ + '\'' +
                ", coupon_id_='" + coupon_id_ + '\'' +
                ", coupon_fee_='" + coupon_fee_ + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", attach='" + attach + '\'' +
                ", time_end='" + time_end + '\'' +
                ", trade_state_desc='" + trade_state_desc + '\'' +
                '}';
    }
}
