package com.scoprion.mall.common;

import com.scoprion.mall.domain.GoodSnapshot;
import com.scoprion.mall.domain.Goods;
import org.springframework.beans.BeanUtils;

/**
 * 业务公用
 *
 * @author by kunlun
 * @created on 2017/11/29.
 */
public class ServiceCommon {

    /**
     * 商品快照组装
     *
     * @param goods
     * @param goodId
     * @return
     */
    public static GoodSnapshot snapshotConstructor(Goods goods, Long goodId) {
        GoodSnapshot goodSnapshot = new GoodSnapshot();
        BeanUtils.copyProperties(goods, goodSnapshot);
        goodSnapshot.setGoodId(goodId);
        goodSnapshot.setGoodDescription(goods.getDescription());
        return null;
    }




}
