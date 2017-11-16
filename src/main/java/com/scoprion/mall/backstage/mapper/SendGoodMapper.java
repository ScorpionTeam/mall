package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.SendGood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * Created on 2017/11/1.
 *
 * @author ycj
 */
@Mapper
public interface SendGoodMapper {

    /**
     * 新增发货信息
     *
     * @param sendGood
     * @return
     */
    int add(SendGood sendGood);

    /**
     * 根据id查详情
     *
     * @param id
     * @return
     */
    SendGood findById(@Param("id") Long id);
}
