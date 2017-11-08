package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author by kunlun
 * @created on 2017/10/10.
 */
@RestController
@RequestMapping("/backstage/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    /**
     * 创建活动
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "创建活动")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody JSONObject jsonObject) {
        Activity activity = jsonObject.getObject("activity", Activity.class);
        JSONArray jsonArray = jsonObject.getJSONArray("goodIdList");
        List<Long> goodIdList = new ArrayList<>();
        for (Object goodId : jsonArray) {
            goodIdList.add(Long.valueOf(goodId.toString()));
        }
//        activity.setGoodIdList(goodIdList);
        return activityService.add(activity);
    }

    /**
     * 根据活动id删除活动
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除活动")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return activityService.deleteById(id);
    }

    /**
     * 修改活动
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "修改活动")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(@RequestBody JSONObject jsonObject) {
        Activity activity = jsonObject.getObject("activity", Activity.class);
        JSONArray jsonArray = jsonObject.getJSONArray("goodIdList");
        List<Long> goodIdList = new ArrayList<>();
        for (Object goodId : jsonArray) {
            goodIdList.add(Long.valueOf(goodId.toString()));
        }
//        activity.setGoodIdList(goodIdList);
        return activityService.update(activity);
    }

    /**
     * 查询活动详情
     *
     * @param id 活动id、主键
     * @return
     */
    @ApiOperation(value = "查询活动详情")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public BaseResult findById(Long id) {
        return activityService.findById(id);
    }

    /**
     * 条件模糊查询活动
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @param type      * 0秒杀, 1拼团,2优选
     * @param status    0正常,1删除
     * @return
     */
    @ApiOperation(value = "条件查询活动")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(int pageNo, int pageSize, String type, String status, String searchKey) {
        return activityService.findByCondition(pageNo, pageSize, type, status, searchKey);
    }


    /**
     * 活动跟商品绑定
     *
     * @param jsonObject
     * @return
     */
    @ApiOperation(value = "活动跟商品绑定")
    @RequestMapping(value = "/bindActivityWithGood", method = RequestMethod.GET)
    public BaseResult bindActivityWithGood(@RequestBody JSONObject jsonObject) {
        //活动id、主键
        Long activityId = jsonObject.getObject("activityId", Long.class);
        //商品id集合
        JSONArray jsonArray = jsonObject.getJSONArray("goodIdList");
        List<Long> goodIdList = new ArrayList<>();
        for (Object goodId : jsonArray) {
            goodIdList.add(Long.valueOf(goodId.toString()));
        }
        return activityService.bindActivityWithGood(activityId, goodIdList);
    }
}
