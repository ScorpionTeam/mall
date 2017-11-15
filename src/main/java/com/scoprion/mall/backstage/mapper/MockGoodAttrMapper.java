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
 * @created on 2017/11/15.
 */
@Mapper
public interface MockGoodAttrMapper {

    /**
     * 创建属性
     *
     * @param attr
     */
    void addAttr(Attr attr);

    /**
     * 查询商品属性
     *
     * @param goodId
     * @return
     */
    List<GoodAttr> findGoodAttr(@Param("goodId") Long goodId);

//    /**
//     * 查询属性
//     * @param attrId
//     * @return
//     */
//    List<Attr> findAttr(@Param("attrId") Long attrId);

    List<AttrValue> findAttrValue(@Param("attrId")Long attrId);

    List<AttrExt> findAttrExt();

    /**
     * 创建属性值
     *
     * @param attrValue
     */
    void addAttrValue(AttrValue attrValue);

    /**
     * 创建商品属性
     *
     * @param goodAttr
     */
    void addGoodAttr(GoodAttr goodAttr);

}
