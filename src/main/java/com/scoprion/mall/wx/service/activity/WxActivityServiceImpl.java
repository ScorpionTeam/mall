package com.scoprion.mall.wx.service.activity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.ActivityGoods;
import com.scoprion.mall.domain.Goods;
import com.scoprion.mall.wx.mapper.WxActivityMapper;
import com.scoprion.mall.wx.mapper.WxGoodMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by fk on 2017/11/12.
 */
@Service
public class WxActivityServiceImpl implements WxActivityService {

    @Autowired
    private WxActivityMapper wxActivityMapper;

    /**
     * 拼团
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult findByGroup(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Activity activity = wxActivityMapper.findByActivityTypeTwo();
        Date currentDate = new Date();
        //查询当前时间是否在活动时间范围内
        if (activity.getStartDate().before(currentDate) && activity.getEndDate().after(currentDate)) {
            //活动商品 (拼团)
            Page<Activity> page = wxActivityMapper.findByGroup();
            return new PageResult(page);
        }
        if (activity.getEndDate().before(currentDate)) {
            Page<Activity> page = wxActivityMapper.findByGroup();
            return new PageResult(page);
        }
        if (activity.getStartDate().after(currentDate)) {
            Page<Activity> page = wxActivityMapper.findByGroup();
            return new PageResult(page);
        }
        Page<Activity> page = wxActivityMapper.findByGroup();
        return new PageResult(page);
    }

    /**
     * 秒杀
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult secKill(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Activity activity = wxActivityMapper.findByActivityTypeOne();
        Date currentDate = new Date();
        if (activity.getStartDate().before(currentDate) && activity.getEndDate().after(currentDate)) {
            //活动商品
            Page<Activity> page = wxActivityMapper.secKill();
            return new PageResult(page);
        }
        if (activity.getEndDate().before(currentDate)) {
            return null;
        }
        if (activity.getStartDate().after(currentDate)) {
            return null;
        }
        Page<Activity> page = wxActivityMapper.secKill();
        return new PageResult(page);
    }

    /**
     * 优选
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult preference(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Activity activity = wxActivityMapper.findByActivityTypeThree();
        Date currentDate = new Date();
        if (activity.getStartDate().before(currentDate) && activity.getEndDate().after(currentDate)) {
            //活动商品
            Page<Activity> page = wxActivityMapper.preference();
            return new PageResult(page);
        }
        if (activity.getEndDate().before(currentDate)) {
            return null;
        }
        if (activity.getStartDate().after(currentDate)) {
            return null;
        }
        Page<Activity> page = wxActivityMapper.preference();
        return new PageResult(page);
    }

}
