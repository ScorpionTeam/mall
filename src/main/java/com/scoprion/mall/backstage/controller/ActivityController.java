package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.backstage.service.activity.ActivityService;
import com.scoprion.mall.domain.Activity;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kunlun
 * @created on 2017/10/10.
 */
@RestController
@RequestMapping("backstage/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    /**
     * 创建活动
     *
     * @param activity
     * @return
     */
    @ApiOperation(value = "创建活动")

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody Activity activity) {
        return activityService.add(activity);
    }

    /**
     * 根据活动id删除活动
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除活动")
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public BaseResult deleteById(Long id) {
        return activityService.deleteById(id);
    }

    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    @ApiOperation(value = "修改活动")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(Activity activity) {
        return activityService.update(activity);
    }

    /**
     * 条件查询活动
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    @ApiOperation(value = "查询活动")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(int pageNo, int pageSize, String searchKey) {
        return activityService.findByCondition(pageNo, pageSize, searchKey);
    }


}
