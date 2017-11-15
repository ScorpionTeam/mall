package com.scoprion.mall.backstage.service.mock;

import com.scoprion.mall.backstage.mapper.MockGoodAttrMapper;
import com.scoprion.mall.domain.Attr;
import com.scoprion.mall.domain.AttrExt;
import com.scoprion.mall.domain.AttrValue;
import com.scoprion.mall.domain.GoodAttr;
import com.scoprion.mall.domain.GoodAttrExt;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/15.
 */
@Service
public class MockGoodAttrServiceImpl implements MockGoodAttrService {

    @Autowired
    private MockGoodAttrMapper mockGoodAttrMapper;

    /**
     * 创建商品属性  属性值
     *
     * @return
     */
    @Override
    public BaseResult add() {

        Attr attr = new Attr();
        attr.setAttrName("颜色");
        attr.setVerNo(1);
        mockGoodAttrMapper.addAttr(attr);

        Attr attr1 = new Attr();
        attr1.setVerNo(1);
        attr1.setAttrName("尺寸");
        mockGoodAttrMapper.addAttr(attr1);

        AttrValue attrValue = new AttrValue();
        attrValue.setAttrId(attr.getId());
        attrValue.setAttrValueName("红色");
        attrValue.setAttrValue("red");
        mockGoodAttrMapper.addAttrValue(attrValue);

        AttrValue attrValue1 = new AttrValue();
        attrValue1.setAttrId(attr.getId());
        attrValue1.setAttrValueName("绿色");
        attrValue1.setAttrValue("green");
        mockGoodAttrMapper.addAttrValue(attrValue1);

        AttrValue attrValue2 = new AttrValue();
        attrValue2.setAttrId(attr1.getId());
        attrValue2.setAttrValueName("小号");
        attrValue2.setAttrValue("M");
        mockGoodAttrMapper.addAttrValue(attrValue2);

        AttrValue attrValue3 = new AttrValue();
        attrValue3.setAttrId(attr1.getId());
        attrValue3.setAttrValueName("中号");
        attrValue3.setAttrValue("L");
        mockGoodAttrMapper.addAttrValue(attrValue3);

        GoodAttr goodAttr = new GoodAttr();
        goodAttr.setAttrId(attr.getId());
        goodAttr.setGoodId(1L);
        goodAttr.setAttrName("颜色");
        goodAttr.setVerNo(1);
        goodAttr.setAttrVisible("1");
        goodAttr.setDefaultValue("黑色");
        goodAttr.setSeq(0);
        goodAttr.setAttrType("1");
        goodAttr.setDisplayType("3");
        mockGoodAttrMapper.addGoodAttr(goodAttr);

        GoodAttr goodAttr1 = new GoodAttr();
        goodAttr1.setAttrId(attr1.getId());
        goodAttr1.setGoodId(1L);
        goodAttr1.setAttrName("尺码");
        goodAttr1.setVerNo(1);
        goodAttr1.setAttrVisible("1");
        goodAttr1.setDefaultValue("x");
        goodAttr1.setSeq(1);
        goodAttr1.setAttrType("1");
        goodAttr1.setDisplayType("3");
        mockGoodAttrMapper.addGoodAttr(goodAttr1);
        return BaseResult.success("属性创建成功");
    }

    /**
     * 查询商品规格属性  属性值
     *
     * @param goodId
     * @return
     */
    @Override
    public BaseResult findAttr(Long goodId) {

        List<AttrExt> attrExts = mockGoodAttrMapper.findAttrExt();
        return BaseResult.success(attrExts);
    }
}
