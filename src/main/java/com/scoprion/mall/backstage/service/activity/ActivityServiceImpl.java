package com.scoprion.mall.backstage.service.activity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.backstage.mapper.ActivityMapper;
import com.scoprion.mall.domain.Activity;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kunlun
 * @created on 2017/11/1.
 */
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
        int validResult = activityMapper.validByName(null, activity.getName());
        if (validResult > 0) {
            return BaseResult.error("not_allowed_repeat_name", "活动名称不可重复");
        }
        int result = activityMapper.add(activity);
        if (result > 0) {
            return BaseResult.success("创建活动成功");
        }
        return BaseResult.error("add_fail", "创建活动失败");
    }

    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    @Override
    public BaseResult update(Activity activity) {
        int validResult = activityMapper.validByName(activity.getId(), activity.getName());
        if (validResult > 0) {
            return BaseResult.error("not_allowed_repeat_name", "活动名称不可重复");
        }
        int result = activityMapper.update(activity);
        if (result > 0) {
            return BaseResult.success("修改活动成功");
        }
        return BaseResult.error("update_fail", "修改活动失败");
    }

    /**
     * 删除活动
     *
     * @param id
     * @return
     */
    @Override
    public BaseResult deleteById(Long id) {
        return null;
    }

    /**
     * 查询活动
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    @Override
    public PageResult findByCondition(int pageNo, int pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Activity> page = activityMapper.findByCondition(searchKey);
        if (page.getTotal() <= 0L) {
            return new PageResult(activityMapper.findByCondition(searchKey));
        }
        return new PageResult(page);
    }
}
