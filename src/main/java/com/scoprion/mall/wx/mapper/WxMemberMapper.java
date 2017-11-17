package com.scoprion.mall.wx.mapper;

import com.scoprion.mall.domain.Suggest;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by Administrator
 * @created on 2017/11/2/002.
 */

@Mapper
public interface WxMemberMapper {


    /**
     * 意见、建议
     *
     * @param suggest
     * @return
     */
    int add(Suggest suggest);

}
