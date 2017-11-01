package com.scoprion.mall.littlesoft.service.goodlist;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.littlesoft.mapper.GoodListLittleSoftMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin1 on 2017/11/1.
 */
@Service
public class GoodListLittleSoftServiceImpl implements GoodListLittleSoftService {

    @Autowired
    private GoodListLittleSoftMapper goodListLittleSoftMapper;

    /**
     * 获取商品列表
     *
     * @param pageNo
     * @param pageSize
     * @param type     类型：0 秒杀, 1 拼团, 2精选
     * @return
     */
    @Override
    public PageResult getGoodList(Integer pageNo, Integer pageSize, String type) {
        PageHelper.startPage(pageNo,pageSize);
        Page<Good> page = new Page<Good>();
        //判断列表类型,获取商品id列表
        if("0".equals(type)){
            page = goodListLittleSoftMapper.secondList();
        }else if("1".equals(type)){
            page = goodListLittleSoftMapper.groupList();
        }else if("2".equals(type)){
            page= goodListLittleSoftMapper.wellChoilceList();
        }else {
            //返回空
            return  new PageResult();
        }

        return new PageResult(page);
    }
}
