package com.scoprion.mall.wx.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fk on 2017/11/12.
 */
@Mapper
public interface WxActivityMapper {

    Page<Activity> group();
}
