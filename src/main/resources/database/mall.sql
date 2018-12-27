/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 27/12/2018 11:59:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_ estimate
-- ----------------------------
DROP TABLE IF EXISTS `t_ estimate`;
CREATE TABLE `t_ estimate` (
  `id` bigint(20) NOT NULL,
  `good_id` varchar(45) DEFAULT NULL COMMENT '商品id',
  `message` varchar(500) DEFAULT NULL COMMENT '评价',
  `user_id` varchar(45) DEFAULT NULL COMMENT '用户id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_activity
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL COMMENT '活动名称',
  `status` char(1) DEFAULT NULL COMMENT '状态  0正常  1删除',
  `activity_type` char(1) DEFAULT NULL COMMENT '活动类型 0 秒杀 1 拼团  2 优选 3 免费试用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `target` char(1) DEFAULT NULL COMMENT '目',
  `num` int(11) DEFAULT NULL COMMENT '活动人数',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_activity_good
-- ----------------------------
DROP TABLE IF EXISTS `t_activity_good`;
CREATE TABLE `t_activity_good` (
  `id` int(11) NOT NULL,
  `activityId` bigint(20) DEFAULT NULL,
  `goodId` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '轮播名称',
  `image_url` varchar(200) DEFAULT NULL COMMENT '轮播图',
  `action_url` varchar(100) DEFAULT NULL COMMENT '跳转链接',
  `visit_total` int(11) DEFAULT NULL COMMENT '访问量',
  `status` char(1) DEFAULT NULL COMMENT '0 正常   1 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_brand
-- ----------------------------
DROP TABLE IF EXISTS `t_brand`;
CREATE TABLE `t_brand` (
  `id` int(11) NOT NULL,
  `brand_name` varchar(45) DEFAULT NULL COMMENT '品牌名称',
  `brand_image_id` varchar(200) DEFAULT NULL COMMENT '图片id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `status` char(1) DEFAULT NULL COMMENT '状态  1入驻  0 退出',
  `short_name` varchar(45) DEFAULT NULL COMMENT '简称',
  `country` varchar(45) DEFAULT NULL COMMENT '所属国家',
  `concat_phone` varchar(45) DEFAULT NULL COMMENT '负责人电话',
  `concat` varchar(45) DEFAULT NULL COMMENT '负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL,
  `category_name` varchar(45) DEFAULT NULL COMMENT '类目名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_delivery
-- ----------------------------
DROP TABLE IF EXISTS `t_delivery`;
CREATE TABLE `t_delivery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `recipients` varchar(45) DEFAULT NULL COMMENT '收件人',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `province` varchar(45) DEFAULT NULL COMMENT '省',
  `city` varchar(45) DEFAULT NULL COMMENT '市',
  `area` varchar(45) DEFAULT NULL COMMENT '区',
  `address` varchar(45) DEFAULT NULL COMMENT '详细地址',
  `post_code` varchar(45) DEFAULT NULL COMMENT '邮编',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `type` varchar(45) DEFAULT NULL COMMENT 'CUSTOM 客户  （默认）\nSELLER  商家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_good
-- ----------------------------
DROP TABLE IF EXISTS `t_good`;
CREATE TABLE `t_good` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `category_id` bigint(20) DEFAULT NULL COMMENT '类目id',
  `good_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `description` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `promotion` decimal(10,2) DEFAULT NULL COMMENT '促销价',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `sale_volumn` int(11) DEFAULT NULL COMMENT '销量',
  `discount` int(11) DEFAULT NULL COMMENT '折扣',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `on_sale` char(1) DEFAULT NULL COMMENT '上下架   1上架 0下架  默认上架',
  `hot` char(1) DEFAULT NULL COMMENT '是否热销  1 是 0否',
  `is_new` char(1) DEFAULT NULL COMMENT '是否新品   1是 0否',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后更新时间',
  `freight` char(1) DEFAULT NULL COMMENT '是否包邮  1是 0否',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌ID',
  `seller_id` bigint(20) DEFAULT NULL COMMENT '商户id',
  `visit_total` int(11) DEFAULT NULL COMMENT '浏览量',
  `good_no` varchar(45) DEFAULT NULL COMMENT '商品编码',
  `main_image_url` varchar(200) DEFAULT NULL COMMENT '主图地址',
  `status` char(1) DEFAULT NULL COMMENT '状态  0正常  1删除',
  `rich_content` text COMMENT '商品富文本描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_good_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `t_good_snapshot`;
CREATE TABLE `t_good_snapshot` (
  `id` bigint(20) NOT NULL,
  `good_snapshot_id` varchar(45) DEFAULT NULL COMMENT '商品快照编码',
  `good_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `good_description` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `promotion` decimal(10,2) DEFAULT NULL COMMENT '促销价',
  `price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `discount` int(11) DEFAULT NULL COMMENT '折扣',
  `main_image_url` varchar(200) DEFAULT NULL COMMENT '商品主图',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `good_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_image_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_image_resource`;
CREATE TABLE `t_image_resource` (
  `id` bigint(20) NOT NULL,
  `target_id` bigint(20) DEFAULT NULL COMMENT '关联ID',
  `url` varchar(45) DEFAULT NULL COMMENT '地址',
  `create_date` varchar(45) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_mall_log
-- ----------------------------
DROP TABLE IF EXISTS `t_mall_log`;
CREATE TABLE `t_mall_log` (
  `id` bigint(20) NOT NULL,
  `good_no` varchar(45) DEFAULT NULL COMMENT '商品编码',
  `seller_no` varchar(45) DEFAULT NULL COMMENT '店铺编码',
  `type` char(1) DEFAULT NULL COMMENT '??',
  `ip_address` varchar(45) DEFAULT NULL COMMENT 'ip地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `description` varchar(100) DEFAULT NULL COMMENT '操作描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `head_image` varchar(200) DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(45) DEFAULT NULL COMMENT '昵称',
  `certificate_id` varchar(45) DEFAULT NULL COMMENT '证件号码',
  `certificate_type` char(1) DEFAULT NULL COMMENT '证件类型',
  `mobile` varchar(45) DEFAULT NULL COMMENT '手机号',
  `born_date` varchar(20) DEFAULT NULL COMMENT '生日',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `status` char(1) DEFAULT NULL COMMENT '状态  1注销  0正常',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `order_no` varchar(45) DEFAULT NULL,
  `good_snapshot_ip` bigint(20) DEFAULT NULL,
  `delivery_id` bigint(20) DEFAULT NULL,
  `order_status` char(1) DEFAULT NULL COMMENT '订单状态',
  `order_type` char(1) DEFAULT NULL COMMENT '订单类型',
  `pay_type` char(1) DEFAULT NULL COMMENT '0 支',
  `message` varchar(100) DEFAULT NULL COMMENT '买家留言',
  `recipients` varchar(45) DEFAULT NULL COMMENT '收件人',
  `phone` varchar(45) DEFAULT NULL COMMENT '收件人电话',
  `province` varchar(45) DEFAULT NULL COMMENT '省',
  `city` varchar(45) DEFAULT NULL COMMENT '市',
  `area` varchar(45) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `post_code` varchar(45) DEFAULT NULL COMMENT '邮政编码',
  `good_name` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `count` int(11) DEFAULT NULL COMMENT '商品数量',
  `wx_order_no` varchar(45) DEFAULT NULL COMMENT '微信订单号',
  `create_date` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_date` datetime DEFAULT NULL COMMENT '付款时间',
  `delivery_date` datetime DEFAULT NULL COMMENT '发货时间',
  `good_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `use_ticket` char(1) DEFAULT NULL COMMENT '是否使用优惠券',
  `ticket_id` bigint(20) DEFAULT NULL COMMENT '优惠券id',
  `prepay_id` varchar(45) DEFAULT NULL COMMENT '预付款订单号',
  `remark` varchar(200) DEFAULT NULL COMMENT '审核备注',
  `order_fee` int(11) DEFAULT NULL COMMENT '订单金额',
  `reduce_fee` int(11) DEFAULT NULL COMMENT '优惠金额',
  `payment_fee` int(11) DEFAULT NULL COMMENT '实付金额',
  `good_fee` int(11) DEFAULT NULL COMMENT '商品金额',
  `freight_fee` int(11) DEFAULT NULL COMMENT '运费',
  `delivery_no` varchar(45) DEFAULT NULL COMMENT '运单号',
  `refund_fee` int(255) DEFAULT NULL,
  `refund_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order_log
-- ----------------------------
DROP TABLE IF EXISTS `t_order_log`;
CREATE TABLE `t_order_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(45) DEFAULT NULL COMMENT '订单号',
  `action` varchar(45) DEFAULT NULL COMMENT '动作',
  `ip_address` varchar(45) DEFAULT NULL COMMENT 'ip地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_point
-- ----------------------------
DROP TABLE IF EXISTS `t_point`;
CREATE TABLE `t_point` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL COMMENT '用户id',
  `point` int(11) DEFAULT NULL COMMENT '积分',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `level_name` varchar(45) DEFAULT NULL COMMENT '等级名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_point_log
-- ----------------------------
DROP TABLE IF EXISTS `t_point_log`;
CREATE TABLE `t_point_log` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL COMMENT '用户id',
  `action` varchar(45) DEFAULT NULL COMMENT '??',
  `current_point` int(11) DEFAULT NULL COMMENT '当前积分',
  `operate_point` int(11) DEFAULT NULL COMMENT '操作积分',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_ticket`;
CREATE TABLE `t_ticket` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '优惠券名称',
  `content` varchar(45) DEFAULT NULL COMMENT '说明',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `count` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `status` char(1) DEFAULT NULL COMMENT '??',
  `money` int(11) DEFAULT NULL COMMENT '优惠券金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(2) DEFAULT '0',
  `sex` varchar(255) DEFAULT NULL,
  `amount` decimal(24,12) unsigned zerofill DEFAULT '000000000000.000000000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_wallet
-- ----------------------------
DROP TABLE IF EXISTS `t_wallet`;
CREATE TABLE `t_wallet` (
  `id` int(11) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `t_walletcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for trade_entrust
-- ----------------------------
DROP TABLE IF EXISTS `trade_entrust`;
CREATE TABLE `trade_entrust` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` char(35) NOT NULL COMMENT '单号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `coin_code` varchar(45) NOT NULL COMMENT '币对',
  `trade_type` tinyint(4) NOT NULL COMMENT '交易类型 0 市价  1限价',
  `target_coin` varchar(10) NOT NULL DEFAULT '目标货币' COMMENT '目标货币',
  `source_coin` varchar(10) NOT NULL DEFAULT '交易货币' COMMENT '交易货币',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `trade_amount` decimal(24,12) NOT NULL DEFAULT '0.000000000000' COMMENT '报单数量',
  `trade_price` decimal(24,12) DEFAULT '0.000000000000' COMMENT '报单价格',
  `deal_amount` decimal(24,12) DEFAULT NULL COMMENT '成交数量',
  `deal_price` decimal(24,12) NOT NULL DEFAULT '0.000000000000' COMMENT '成交金额',
  `uncome_buy_amount` decimal(24,12) DEFAULT NULL,
  `uncome_sell_amount` decimal(24,12) DEFAULT NULL,
  `deal_time` datetime DEFAULT NULL COMMENT '成交时间',
  `trade_err_no` int(11) DEFAULT NULL COMMENT '错误码',
  `poundage_amount` decimal(24,12) DEFAULT '0.000000000000' COMMENT '手续费',
  `position` int(11) DEFAULT NULL COMMENT '方向  0 买入 1卖出',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `check_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ORDER_NO` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=210147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for trade_entrust_detail
-- ----------------------------
DROP TABLE IF EXISTS `trade_entrust_detail`;
CREATE TABLE `trade_entrust_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `order_no` varchar(45) NOT NULL COMMENT '委托单号',
  `lp` varchar(45) NOT NULL COMMENT '上游交易所id  如果是本地则返回combo',
  `under_deal_price` decimal(24,12) NOT NULL DEFAULT '0.000000000000' COMMENT '本地成交价',
  `above_deal_price` decimal(24,12) NOT NULL DEFAULT '0.000000000000' COMMENT '上游成交价',
  `above_order_id` varchar(45) DEFAULT NULL,
  `deal_amount` decimal(24,12) NOT NULL DEFAULT '0.000000000000' COMMENT '成交数量',
  `time` bigint(20) NOT NULL DEFAULT '0' COMMENT '成交时间Long',
  `trade_type` int(11) NOT NULL COMMENT '交易类型 0市价 1限价',
  `lp_order_id` varchar(45) NOT NULL COMMENT '交易对手id',
  `deal_date_time` datetime NOT NULL COMMENT '成交时间',
  `poundage_amount` decimal(24,12) NOT NULL COMMENT '手续费',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `check_str` varchar(45) DEFAULT NULL COMMENT '加密字符串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=388064 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
