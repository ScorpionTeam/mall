package com.scoprion.mall.wx.service.point;

import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.Point;
import com.scoprion.mall.domain.PointLog;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.mapper.WxPointLogMapper;
import com.scoprion.mall.wx.mapper.WxPointMapper;
import com.scoprion.result.BaseResult;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
@Service
public class WxPointServiceImpl implements WxPointService {

    @Autowired
    private WxPointMapper wxPointMapper;

    @Autowired
    private WxPointLogMapper wxPointLogMapper;

    @Autowired
    private WxOrderMapper wxOrderMapper;

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public BaseResult findByUserId(String userId) {
        Point point = wxPointMapper.findByUserId(userId);
        return BaseResult.success(point);
    }

    /**
     * 个人积分
     * @param id
     * @param userId
     * @return
     */
    @Override
    public BaseResult personalScore(Long id, String userId) {
        Order order = wxOrderMapper.personalScore(id, userId); //userID goodFee
        double goodFee = order.getGoodFee();  //500
        int score = (int) Math.floor(goodFee / 10); //买商品消耗积分  50

        PointLog pointLog = wxPointLogMapper.personalScore(id, userId);

        Integer currentPoint = pointLog.getCurrentPoint(); //当前积分  2
        //userId = pointLog.getUserId();
        if (currentPoint > score) {
            currentPoint = currentPoint - score;
        } else {
            currentPoint = 0;
        }
        wxPointLogMapper.personal(userId,currentPoint);
        //pointLog.setUserId(userId);
        //pointLog.setCurrentPoint(currentPoint);

        return BaseResult.success("操作成功");

    }


        /*if(score >= currentPoint){
            operatePoint = currentPoint + operatePoint;
            currentPoint = 0;
        }else {
            operatePoint = currentPoint + operatePoint;
            currentPoint = currentPoint - score;
        }
        pointLog.setCurrentPoint(currentPoint);
        pointLog.setOperatePoint(operatePoint);
        int result = wxPointLogMapper.personal(id,userId);
        if (result > 0){
            return BaseResult.success("积分扣减成功");
        }
        return BaseResult.error("personal_fail","积分扣减失败");*/



}
