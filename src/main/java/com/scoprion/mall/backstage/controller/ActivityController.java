package com.scoprion.mall.backstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.scoprion.annotation.Access;
import com.scoprion.mall.backstage.service.activity.ActivityService;
import com.scoprion.mall.domain.Activity;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * @param activity
     * @return
     */
    @Access
    @ApiOperation(value = "创建活动")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResult add(@RequestBody Activity activity) {
        return activityService.add(activity);
    }

    /**
     * 批量修改活动
     *
     * @param jsonObject
     * @return
     */
    @Access
    @ApiOperation(value = "批量修改活动")
    @RequestMapping(value = "/batchModifyStatus", method = RequestMethod.POST)
    public BaseResult batchModifyStatus(@RequestBody JSONObject jsonObject) {
        String status = jsonObject.getString("status");
        List<Long> idList = jsonObject.getJSONArray("idList").toJavaList(Long.class);
        return activityService.batchModifyStatus(status, idList);
    }


    /**
     * 根据活动id删除活动
     *
     * @param id
     * @return
     */
    @Access
    @ApiOperation(value = "删除活动")
    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    public BaseResult deleteById(Long id) {
        return activityService.deleteById(id);
    }

    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    @Access
    @ApiOperation(value = "修改活动")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public BaseResult modify(@RequestBody Activity activity) {
        return activityService.modify(activity);
    }

    /**
     * 查询活动详情
     *
     * @param id 活动id、主键
     * @return
     */
    @Access
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
     * @param type      * 0秒杀, 1拼团,2优选,3试用，4全部
     * @param status    0正常,1删除
     * @return
     */
    @Access
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
    @Access
    @ApiOperation(value = "活动跟商品绑定")
    @RequestMapping(value = "/bindActivityWithGood", method = RequestMethod.POST)
    public BaseResult bindActivityWithGood(@RequestBody JSONObject jsonObject) {
        //活动id、主键
        Long activityId = jsonObject.getObject("activityId", Long.class);
        //商品id集合
        List<Goods> goodList = jsonObject.getJSONArray("goodList").toJavaList(Goods.class);
        return activityService.bindActivityWithGood(activityId, goodList);
    }

    /**
     * 活动跟商品解绑
     *
     * @param jsonObject
     * @return
     */
    @Access
    @ApiOperation(value = "活动跟商品解绑")
    @RequestMapping(value = "/unbindActivityWithGood", method = RequestMethod.POST)
    public BaseResult unbindActivityWithGood(@RequestBody JSONObject jsonObject) {
        //活动id、主键
        Long activityId = jsonObject.getLong("activityId");
        //商品id集合
        List<Long> goodIdList = jsonObject.getJSONArray("goodIdList").toJavaList(Long.class);
        return activityService.unbindActivityWithGood(activityId, goodIdList);
    }

}
