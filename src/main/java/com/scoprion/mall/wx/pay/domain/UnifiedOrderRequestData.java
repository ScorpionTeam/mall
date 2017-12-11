package com.scoprion.mall.wx.pay.domain;

import java.util.SortedMap;

import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.mall.wx.pay.WxPayConfig;

import java.lang.reflect.Field;
import java.util.TreeMap;

/**
 * @author by kunlun
 * @created on 2017/11/4.
 */
public class UnifiedOrderRequestData {

    /**
     * 小程序id
     */
    private String appid;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 设备号
     */
    private String device_info;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 签名
     */
    private String sign;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 货币类型
     */
    private String fee_type;

    /**
     * 总金额
     */
    private int total_fee;

    /**
     * 终端IP
     */
    private String spbill_create_ip;

    /**
     * 交易起始时间
     */
    private String time_start;

    /**
     * 交易结束时间
     */
    private String time_expire;

    /**
     * 商品标记
     */
    private String goods_tag;

    /**
     * 通知地址
     */
    private String notify_url;

    /**
     * 通知地址(拼团)
     */
    private String notify_url_group;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 商品id
     */
    private String product_id;

    /**
     * 指定支付方式
     */
    private String limit_pay;

    /**
     * 用户标识
     */
    private String openid;


    /**
     * 按照 自然序排列
     *
     * @return
     */
    public SortedMap<String, Object> toMap() {
        SortedMap<String, Object> map = new TreeMap<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public UnifiedOrderRequestData(UnifiedOrderReqDataBuilder builder) {
        this.appid = builder.appid;
        this.mch_id = builder.mch_id;
        this.device_info = builder.device_info;
        this.nonce_str = WxUtil.createRandom(true, 10);
        this.body = builder.body;
        this.detail = builder.detail;
        this.attach = builder.attach;
        this.out_trade_no = builder.out_trade_no;
        this.fee_type = builder.fee_type;
        this.total_fee = builder.total_fee;
        this.spbill_create_ip = builder.spbill_create_ip;
        this.time_start = builder.time_start;
        this.time_expire = builder.time_expire;
        this.goods_tag = builder.goods_tag;
        this.notify_url = builder.notify_url;
        this.notify_url_group = builder.notify_url_group;
        this.trade_type = builder.trade_type;
        this.product_id = builder.product_id;
        this.limit_pay = builder.limit_pay;
        this.openid = builder.openid;
        this.nonce_str = builder.nonce_str;
        this.sign = WxUtil.MD5(toMap().toString());
    }

    public static class UnifiedOrderReqDataBuilder {
        private String appid;
        private String mch_id;
        private String device_info;
        private String body;
        private String detail;
        private String attach;
        private String out_trade_no;
        private String fee_type;
        private int total_fee;
        private String spbill_create_ip;
        private String time_start;
        private String time_expire;
        private String goods_tag;
        private String notify_url;
        private String notify_url_group;
        private String trade_type;
        private String product_id;
        private String limit_pay;
        private String openid;
        private String nonce_str;

        /**
         * 分享一下这样设计的好处:不暴露机密信息 ，同时区分出那些是外部传入信息和本地服务器信息
         *
         * @param body             //商品描述
         * @param out_trade_no     //订单号
         * @param total_fee        //总的金额
         * @param spbill_create_ip //ip地址
         * @param trade_type       //支付类型
         */
        public UnifiedOrderReqDataBuilder(String body, String out_trade_no,
                                          Integer total_fee, String spbill_create_ip, String trade_type) {

            this(WxPayConfig.APP_ID, WxPayConfig.MCHID, WxPayConfig.NOTIFY_URL, WxPayConfig.NOTIFY_URL_GROUP,WxPayConfig.FREE_URL_GROUP,
                    body, out_trade_no, total_fee, spbill_create_ip, trade_type);
        }

        public UnifiedOrderReqDataBuilder(String appid, String mch_id,
                                          String notify_url, String notify_url_group, String free_url_group,String body, String out_trade_no,
                                          Integer total_fee, String spbill_create_ip, String trade_type) {
            // 校验外部传入数据
            if (appid == null) {
                throw new IllegalArgumentException("传入参数appid不能为null");
            }
            if (mch_id == null) {
                throw new IllegalArgumentException("传入参数mch_id不能为null");
            }
            if (body == null) {
                throw new IllegalArgumentException("传入参数body不能为null");
            }
            if (out_trade_no == null) {
                throw new IllegalArgumentException("传入参数out_trade_no不能为null");
            }
            if (total_fee == null) {
                throw new IllegalArgumentException("传入参数total_fee不能为null");
            }
            if (spbill_create_ip == null) {
                throw new IllegalArgumentException(
                        "传入参数spbill_create_ip不能为null");
            }
            if (trade_type == null) {
                throw new IllegalArgumentException("传入参数trade_type不能为null");
            }
            this.appid = appid;
            this.mch_id = mch_id;
            this.body = body;
            this.out_trade_no = out_trade_no;
            this.total_fee = total_fee;
            this.spbill_create_ip = spbill_create_ip;
            this.notify_url = notify_url;
            this.notify_url_group = notify_url_group;
            this.trade_type = trade_type;
            this.nonce_str = WxUtil.createRandom(true, 10);
        }

        // 添加额外信息
        public UnifiedOrderReqDataBuilder setDevice_info(String device_info) {
            this.device_info = device_info;
            return this;
        }

        public UnifiedOrderReqDataBuilder setDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public UnifiedOrderReqDataBuilder setAttach(String attach) {
            this.attach = attach;
            return this;
        }

        public UnifiedOrderReqDataBuilder setFee_type(String fee_type) {
            this.fee_type = fee_type;
            return this;
        }

        public UnifiedOrderReqDataBuilder setTime_start(String time_start) {
            this.time_start = time_start;
            return this;
        }

        public UnifiedOrderReqDataBuilder setTime_expire(String time_expire) {
            this.time_expire = time_expire;
            return this;
        }

        public UnifiedOrderReqDataBuilder setGoods_tag(String goods_tag) {
            this.goods_tag = goods_tag;
            return this;
        }

        public UnifiedOrderReqDataBuilder setProduct_id(String product_id) {
            this.product_id = product_id;
            return this;
        }

        public UnifiedOrderReqDataBuilder setLimit_pay(String limit_pay) {
            this.limit_pay = limit_pay;
            return this;
        }

        public UnifiedOrderReqDataBuilder setOpenid(String openid) {
            this.openid = openid;
            return this;
        }

        public UnifiedOrderRequestData build() {

            if ("JSAPI".equals(this.trade_type) && this.openid == null) {
                throw new IllegalArgumentException(
                        "当传入trade_type为JSAPI时，openid为必填参数");
            }
            if ("NATIVE".equals(this.trade_type) && this.product_id == null) {
                throw new IllegalArgumentException(
                        "当传入trade_type为NATIVE时，product_id为必填参数");
            }
            // app 支付不需要以上参数 oh i got it 每个平台相应支付所需请求参数是不一样哒 请留意官方api
            return new UnifiedOrderRequestData(this);
        }

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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getNotify_url_group() {
        return notify_url_group;
    }

    public void setNotify_url_group(String notify_url_group) {
        this.notify_url_group = notify_url_group;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "UnifiedOrderRequestData{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", body='" + body + '\'' +
                ", detail='" + detail + '\'' +
                ", attach='" + attach + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", total_fee=" + total_fee +
                ", spbill_create_ip='" + spbill_create_ip + '\'' +
                ", time_start='" + time_start + '\'' +
                ", time_expire='" + time_expire + '\'' +
                ", goods_tag='" + goods_tag + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", notify_url_group='" + notify_url_group + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", product_id='" + product_id + '\'' +
                ", limit_pay='" + limit_pay + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
