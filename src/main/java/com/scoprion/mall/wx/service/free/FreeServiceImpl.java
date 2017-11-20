package com.scoprion.mall.wx.service.free;

import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.wx.mapper.FreeMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kunlun
 * @created on 2017/11/20.
 */
@Service
public class FreeServiceImpl implements FreeService {

    @Autowired
    private FreeMapper freeMapper;

    /**
     * 查询试用商品列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findAll(int pageNo, int pageSize) {
        return null;
    }

    /**
     * 参加试用活动
     *
     * @param activityId
     * @param wxCode
     * @return
     */
    @Override
    public BaseResult apply(Long activityId, String wxCode) {
        String openId = WxUtil.getOpenId(wxCode);
        //查询是否参加过该活动
        int result = freeMapper.validByActivityId(activityId,openId);
        if(result>0){
            return BaseResult.error("apply_fail","您已参加过该活动");
        }
        //查询活动详情
        Activity activity = freeMapper.findById(activityId);
        return null;
    }

}
