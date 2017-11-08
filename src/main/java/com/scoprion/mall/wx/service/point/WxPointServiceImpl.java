package com.scoprion.mall.wx.service.point;

import com.scoprion.mall.domain.Point;
import com.scoprion.mall.wx.mapper.WxPointMapper;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
@Service
public class WxPointServiceImpl implements WxPointService {

    @Autowired
    private WxPointMapper wxPointMapper;

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public BaseResult findByUserId(String userId) {
        Point point = wxPointMapper.findByUserId(userId);
        return BaseResult.success(point);
    }


}
