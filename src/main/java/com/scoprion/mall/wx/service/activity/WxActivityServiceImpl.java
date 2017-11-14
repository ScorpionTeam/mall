package com.scoprion.mall.wx.service.activity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.wx.mapper.WxActivityMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fk on 2017/11/12.
 */
@Service
public class WxActivityServiceImpl implements WxActivityService {

    @Autowired
    private WxActivityMapper wxActivityMapper;

    /**
     * 抢购拼团
     * @param pageNo
     * @param pageSize
     * @return
     */

    @Override
    public PageResult findByGroup(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        //活动商品
        Page<Activity> page = wxActivityMapper.findByGroup();
        return new PageResult(page);
    }
}