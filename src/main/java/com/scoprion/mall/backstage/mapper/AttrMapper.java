package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.Attr;
import com.scoprion.mall.domain.AttrValue;
import com.scoprion.mall.domain.GoodAttr;
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
    int addAttrValue(@Param("attrValueList") List<AttrValue> attrValueList);

    /**
     * 创建商品属性
     *
     * @param goodAttr
     * @return
     */
    int addGoodAttr(GoodAttr goodAttr);


}
