package com.scoprion.mall.backstage.service.attr;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.mall.backstage.mapper.AttrMapper;
import com.scoprion.mall.domain.Attr;
import com.scoprion.mall.domain.AttrExt;
import com.scoprion.mall.domain.AttrValue;
import com.scoprion.mall.domain.GoodAttr;
import com.scoprion.result.BaseResult;
import freemarker.ext.beans.IteratorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/16.
 */
@Service
public class AttrServiceImpl implements AttrService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttrService.class);

    @Autowired
    private AttrMapper attrMapper;

    /**
     * 创建规格
     *
     * @param json
     * @return
     */
    @Override
    public BaseResult add(JSONObject json) {
        Long goodId = json.getLong("goodId");
        List<AttrExt> attrExts = json.getJSONArray("attrExts").toJavaList(AttrExt.class);
        attrExts.forEach(item -> {
            attrMapper.addAttr(item);
            //属性值集合
            List<AttrValue> attrValues = item.getAttrValueList();
            attrValues.forEach(item1 -> {
                item1.setAttrId(item.getId());
            });
            attrMapper.addAttrValue(attrValues);
            GoodAttr goodAttr = goodAttrConstructor(goodId, item);
            attrMapper.addGoodAttr(goodAttr);
        });

        return null;
    }

    /**
     * 产品属性封装
     *
     * @return
     */
    private GoodAttr goodAttrConstructor(Long goodId, AttrExt attrExt) {
        GoodAttr goodAttr = new GoodAttr();
        goodAttr.setGoodId(goodId);
        goodAttr.setAttrId(attrExt.getId());
        goodAttr.setAttrName(attrExt.getAttrName());
        goodAttr.setVerNo(1);
        goodAttr.setAttrVisible("1");
        goodAttr.setDefaultValue("");
        goodAttr.setSeq(attrExt.getSeq());
        goodAttr.setAttrType("0");
        goodAttr.setDisplayType("3");
        return goodAttr;
    }


}
