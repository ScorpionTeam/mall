package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fk
 * @create on 2017/11/12.
 */
@Mapper
public interface WxActivityMapper {


    //抢购拼团
    Page<Activity> findByGroup();
}
