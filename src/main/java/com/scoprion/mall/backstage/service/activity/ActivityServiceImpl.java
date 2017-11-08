package com.scoprion.mall.backstage.service.activity;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.backstage.mapper.ActivityMapper;
import com.scoprion.mall.domain.Activity;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        int result = activityMapper.add(activity);
        if (result == 0) {
            return BaseResult.error("add_fail", "创建活动失败");
        }
//        bindActivityWithGood(activity.getId(), activity.getGoodIdList());
        return BaseResult.success("创建活动成功");
    }


    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    @Override
    public BaseResult update(Activity activity) {
        if (activity.getId() == null) {
            return BaseResult.parameterError();
        }
        int validResult = activityMapper.validByName(activity.getName());
        if (validResult > 0) {
            return BaseResult.error("not_allowed_repeat_name", "活动名称不可重复");
        }
        int result = activityMapper.update(activity);
        if (result == 0) {
            return BaseResult.error("update_fail", "修改活动失败");
        }
//        bindActivityWithGood(activity.getId(), activity.getGoodIdList());
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
     * @param type      * 0秒杀, 1拼团,2优选
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
        List<Long> goodIdList = activityMapper.findGoodIdByActivityId(id);
//        activity.setGoodIdList(goodIdList);
        return BaseResult.success(activity);
    }

    @Override
    public BaseResult bindActivityWithGood(Long activityId, List<Long> goodIdList) {
        if (activityId == null || goodIdList == null || goodIdList.size() == 0) {
            return BaseResult.parameterError();
        }
        //活动跟商品绑定
        for (Long goodId : goodIdList) {
            //查询活动与商品匹配关系
            int activityCount = activityMapper.findActivityByGoodId(goodId);
            if (activityCount > 0) {
                //存在已经绑定的关系，清空掉
                activityMapper.deleteActivityGood(goodId);
            }
            //添加活动与商品匹配关系
            activityMapper.addActivityGood(activityId, goodId);
        }
        return BaseResult.success("活动绑定成功");
    }
}
