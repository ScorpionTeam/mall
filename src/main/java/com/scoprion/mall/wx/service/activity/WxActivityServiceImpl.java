package com.scoprion.mall.wx.service.activity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.wx.mapper.WxActivityMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fk on 2017/11/12.
 */
@Service
public class WxActivityServiceImpl implements WxActivityService {

    @Autowired
    private WxActivityMapper wxActivityMapper;

    /**
     * 抢购拼团
     * @param activityType
     * @param goodIdList
     * @return
     */
    @Override
    public PageResult group(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Activity> page = wxActivityMapper.group();
        return new PageResult(page);
    }
}
