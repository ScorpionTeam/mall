package com.scoprion.mall.littlesoft.service.goodlist;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.mall.littlesoft.mapper.GoodListWxMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by admin1 on 2017/11/1.
 */
@Service
public class GoodListWxServiceImpl implements GoodWxService {

    @Autowired
    private GoodListWxMapper goodListWxMapper;

    /**
     * 获取商品列表
     *
     * @param pageNo
     * @param pageSize
     * @param type     类型：0秒杀 1拼团 2优选
     * @return
     */
    @Override
    public PageResult getGoodList(Integer pageNo, Integer pageSize, String type) {
        PageHelper.startPage(pageNo,pageSize);
        //判断列表类型,获取商品id列表
        if(type == null){
            //返回空
            return  new PageResult();
        }

        Page<GoodExt> page = goodListWxMapper.getGoodList(type);

        return new PageResult(page);
    }
}
