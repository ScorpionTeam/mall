package com.scoprion.mall.backstage.service.activity;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.backstage.mapper.ActivityGoodMapper;
import com.scoprion.mall.backstage.mapper.ActivityMapper;
import com.scoprion.mall.backstage.mapper.GoodLogMapper;
import com.scoprion.mall.backstage.mapper.GoodsMapper;
import com.scoprion.mall.common.ServiceCommon;
import com.scoprion.mall.domain.*;
import com.scoprion.mall.domain.good.GoodLog;
import com.scoprion.mall.domain.good.Goods;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    private ActivityGoodMapper activityGoodMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodLogMapper goodLogMapper;

    /**
     * 创建活动
     *
     * @param activity
     * @return
     */
    @Override
    public BaseResult add(Activity activity) {
        if (StringUtils.isEmpty(activity.getActivityType())) {
            return BaseResult.error("ERROR", "活动类型不能为空");
        }
        if (StringUtils.isEmpty(activity.getName())) {
            return BaseResult.error("ERROR", "活动名称不能为空");
        }
        int validResult = activityMapper.validByName(activity.getName());
        if (validResult > 0) {
            return BaseResult.error("ERROR", "活动名称不可重复");
        }
        int typeResult = activityMapper.validByTypeAndTime(activity.getStartDate(),
                activity.getEndDate(), activity.getActivityType());
        if (typeResult > 0) {
            return BaseResult.error("ERROR", "相同时间段内，不允许创建相同类型的活动");
        }
        int result = activityMapper.add(activity);
        if (result == 0) {
            return BaseResult.error("ERROR", "创建活动失败");
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
            return BaseResult.error("ERROR", "活动名称不可重复");
        }
        int result = activityMapper.modify(activity);
        if (result == 0) {
            return BaseResult.error("ERROR", "修改活动失败");
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
        return BaseResult.error("ERROR", "删除活动失败");
    }

    /**
     * 查询活动
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @param type      ECONDS_KILL 秒杀 SPELL_GROUP 拼团 PERFERRED 优选 FREE试用
     * @param status    删除 NORMAL, 正常,UN_NORMAL, 非正常
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
        if (CommonEnum.ALL.getCode().equals(type)) {
            //全部
            type = null;
        }
        Page<Activity> page = activityMapper.findByCondition(type, status, searchKey);
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
     * @param status 状态
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
            BaseResult.error("ERROR", "修改失败");
        }
        if (idList.size() > result) {
            BaseResult.success("部分修改成功，其余数据请刷新后重试");
        }
        return BaseResult.success("修改成功");
    }

    @Override
    public BaseResult bindActivityWithGood(Long activityId, List<Goods> goodList) {
        if (activityId == null || goodList == null || goodList.size() == 0) {
            return BaseResult.parameterError();
        }
        Activity activity = activityMapper.findById(activityId);
        if (CommonEnum.EXPIRE.getCode().equals(activity.getStatus())) {
            //已过期
            return BaseResult.error("ERROR", "活动已过期，不能绑定");
        }
        if (activity.getEndDate().before(new Date())) {
            activity.setStatus(CommonEnum.EXPIRE.getCode());
            activityMapper.modify(activity);
            return BaseResult.error("ERROR", "活动已过期，不能绑定");
        }
        //活动跟商品绑定
        for (Goods good : goodList) {
            //添加活动与商品匹配关系
            activityGoodMapper.bindActivityGood(activityId, good.getId(), CommonEnum.NORMAL.getCode(), good.getStock());
            //商品库存扣减
            goodsMapper.modifyGoodsDeduction(good.getId(), -good.getStock());
            saveGoodLog(good.getId(), "商品绑定活动，商品库存扣减" + good.getStock());
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
        goodIdList.forEach(goodId -> {
            ActivityGoods item = activityGoodMapper.findByGoodIdAndActivityId(goodId, activityId);
            //已过期的活动，正在进行的活动
            int result = activityGoodMapper.unbindActivityWithGood(goodId);
            if (result > 0 && item != null && item.getStock() > 0) {
                //解绑成功，查看参加活动的商品是否有剩余，有剩余则返回原商品库存
                //返回原有库存
                goodsMapper.modifyGoodsDeduction(goodId, item.getStock());
                saveGoodLog(goodId, "解绑活动跟商品关系，商品库存返还" + item.getStock());
            }
        });
        return BaseResult.success("解绑成功");
    }

    private void saveGoodLog(Long goodId, String action) {
        ServiceCommon.saveGoodLog(null, action, goodId, goodLogMapper);
    }
}
