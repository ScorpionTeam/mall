package com.scoprion.mall.domain;

import java.math.BigDecimal;

/**
 * Created on 2017/9/28.
 */
public class Good {

    //主键
    private Long id;

    //类目
    private Long categoryId;

    //商品名称
    private String name;

    //商品描述
    private String description;

    //促销价格
    private BigDecimal promotion;

    //价格
    private BigDecimal price;

    //销量
    private int saleVolume;

    //折扣
    private int discount;

    //库存
    private int repertory;

    //券
    private Long ticketId;

}
