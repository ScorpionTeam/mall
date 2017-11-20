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
    /**
     * 拼团
     * @return
     */
    Page<Activity> findByGroup();

    /**
     * 秒杀
     * @return
     */
    Page<Activity> secKill();

    /**
     * 优选
     * @return
     */
    Page<Activity> preference();

}
