package com.scoprion.mall.backstage.service.activity;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.ActivityMapper;
import com.scoprion.mall.backstage.mapper.GoodsMapper;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.GoodExt;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/11/1.
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 创建活动
     *
     * @param activity
     * @return
     */
    @Override
    public BaseResult add(Activity activity) {
        if (StringUtils.isEmpty(activity.getName())) {
            return BaseResult.error("param_error", "活动名称不能为空");
        }
        int validResult = activityMapper.validByName(activity.getName());
        if (validResult > 0) {
            return BaseResult.error("not_allowed_repeat_name", "活动名称不可重复");
        }
        int typeResult = activityMapper.validByTypeAndTime(activity.getStartDate(),
                activity.getEndDate(), activity.getActivityType());
        if (typeResult > 0) {
            return BaseResult.error("not_allowed_repeat_type", "相同时间段内，不允许创建相同类型的活动");
        }
        int result = activityMapper.add(activity);
        if (result == 0) {
            return BaseResult.error("add_fail", "创建活动失败");
        }
        return BaseResult.success("创建活动成功");
    }


    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    @Override
    public BaseResult modify(Activity activity) {
        if (activity.getId() == null) {
            return BaseResult.parameterError();
        }
        int validResult = activityMapper.validByNameAndId(activity.getId(), activity.getName());
        if (validResult > 0) {
            return BaseResult.error("not_allowed_repeat_name", "活动名称不可重复");
        }
        int result = activityMapper.modify(activity);
        if (result == 0) {
            return BaseResult.error("update_fail", "修改活动失败");
        }
        return BaseResult.success("修改活动成功");
    }

    /**
     * 删除活动
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteById(Long id) {
        int result = activityMapper.deleteById(id);
        if (result > 0) {
            return BaseResult.success("删除活动成功");
        }
        return BaseResult.error("del_error", "删除活动失败");
    }

    /**
     * 查询活动
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @param type      * 0秒杀, 1拼团,2优选，3全部
     * @param status    0正常,1删除
     * @return
     */
    @Override
    public PageResult findByCondition(int pageNo, int pageSize, String type, String status, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        if (Constant.STATUS_THREE.equals(type)) {
            //全部
            type = null;
        }
        Page<Activity> page = activityMapper.findByCondition(type, status, searchKey);
        if (page.getTotal() <= 0L) {
            return new PageResult(new ArrayList());
        }
        return new PageResult(page);
    }


    /**
     * 根据ID查询活动详情
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        Activity activity = activityMapper.findById(id);
        if (activity == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(activity);
    }

    /**
     * 批量修改活动状态
     *
     * @param status 状态 0正常 1删除
     * @param idList id集合
     * @return BaseResult
     */
    @Override
    public BaseResult batchModifyStatus(String status, List<Long> idList) {
        if (StringUtils.isEmpty(status) || idList == null || idList.size() == 0) {
            return BaseResult.parameterError();
        }
        int result = activityMapper.batchModifyStatus(status, idList);
        if (result == 0) {
            BaseResult.error("error", "修改失败");
        }
        if (idList.size() > result) {
            BaseResult.success("部分修改成功，其余数据请刷新后重试");
        }
        return BaseResult.success("修改成功");
    }

    @Override
    public BaseResult bindActivityWithGood(Long activityId, List<Long> goodIdList) {
        if (activityId == null || goodIdList == null || goodIdList.size() == 0) {
            return BaseResult.parameterError();
        }
        //活动跟商品绑定
        for (Long goodId : goodIdList) {
            //查询活动与商品匹配关系
            GoodExt good = goodsMapper.findById(goodId);
            if (good.getActivityId() != null) {
                //商品存在已经绑定的关系
                Activity activity = activityMapper.findById(good.getActivityId());
                if (activity.getStartDate().after(new Date())) {
                    //活动还未开始
                    continue;
                } else if (activity.getEndDate().before(new Date())) {
                    //活动已经结束
                    //商品存在已经绑定的关系
                    activityMapper.deleteActivityGood(goodId);
                } else {
                    //活动进行中
                    continue;
                }
            }
            //添加活动与商品匹配关系
            activityMapper.addActivityGood(activityId, goodId);
        }
        return BaseResult.success("活动绑定成功");
    }

    /**
     * 解绑活动跟商品关系
     *
     * @param activityId
     * @param goodIdList
     * @return
     */
    @Override
    public BaseResult unbindActivityWithGood(Long activityId, List<Long> goodIdList) {
        if (activityId == null || goodIdList == null || goodIdList.size() == 0) {
            return BaseResult.parameterError();
        }
        int result = activityMapper.unbindActivityWithGood(activityId, goodIdList);
        if (result == 0) {
            return BaseResult.error("unbind_error", "解绑失败");
        }
        return BaseResult.success("解绑成功");
    }
}
