package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.Attr;
import com.scoprion.mall.domain.AttrExt;
import com.scoprion.mall.domain.AttrValue;
import com.scoprion.mall.domain.GoodAttr;
import com.scoprion.mall.domain.GoodAttrExt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/16.
 */
@Mapper
public interface AttrMapper {

    /**
     * 创建属性
     *
     * @param attr
     */
    int addAttr(Attr attr);


    /**
     * 创建属性值
     *
     * @param attrValueList
     * @return
     */
    int addAttrValue(List<AttrValue> attrValueList);

    /**
     * 创建商品属性
     *
     * @param goodAttr
     * @return
     */
    int addGoodAttr(GoodAttr goodAttr);

    /**
     * 根据商品id查询规格属性
     *
     * @param goodId
     * @return
     */

    List<GoodAttrExt> findGoodAttrByGoodId(@Param("goodId") Long goodId);

    /**
     * 查询规格属性
     *
     * @return
     */
    List<AttrExt> findAttrExt(@Param("attrId") Long attrId);


}
