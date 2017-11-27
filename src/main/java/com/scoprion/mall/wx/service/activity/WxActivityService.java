package com.scoprion.mall.wx.service.activity;

import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.domain.OrderExt;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

import java.util.List;

/**
 * Created by fk
 *
 * @create on 2017/11/12.
 */
public interface WxActivityService {

    /**
     * 拼团
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult findByGroup(int pageNo, int pageSize);

    /**
     * 秒杀
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult secKill(int pageNo, int pageSize);


    /**
     * 优选
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResult preference(int pageNo, int pageSize);

    /**
     * 参加拼团
     *
     * @param orderExt
     * @param ipAddress
     * @return
     */
    BaseResult group(OrderExt orderExt, String ipAddress);
}
