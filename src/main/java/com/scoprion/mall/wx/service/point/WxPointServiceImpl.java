package com.scoprion.mall.wx.service.point;

import com.scoprion.mall.domain.ActivityExt;
import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.Point;
import com.scoprion.mall.domain.PointLog;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.mapper.WxPointLogMapper;
import com.scoprion.mall.wx.mapper.WxPointMapper;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     *
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
        userId = pointLog.getUserId();
        String action = wxPointLogMapper.personalScore(id, userId).toString();
        if (action == "0") {
            if (currentPoint >= score) {
                currentPoint = currentPoint - score;
            } else {
                currentPoint = 0;
            }
        } else {
            currentPoint = currentPoint + score;
        }

        wxPointLogMapper.personal(userId, currentPoint);
        return BaseResult.success("操作成功");
    }

    /**
     * 等级划分
     *
     * @param userId
     * @return
     */
    @Override
    public BaseResult grade(String userId) {
        Point point = wxPointMapper.grades(userId);
        PointLog pointLog = wxPointLogMapper.grade(userId);
        int score = point.getPoint();
        int operatePoint = pointLog.getOperatePoint();
        int integral = score + operatePoint;
        point.setPoint(integral);
        if (integral < 36) {
            int level = 1;
            String levelName = "青铜";
            point.setLevel(level);
            point.setLevelName(levelName);
        } else if (integral >= 36 || integral < 750) {
            int level = 2;
            String levelName = "白银";
            point.setLevel(level);
            point.setLevelName(levelName);
        } else if (integral >= 750 || integral < 1500) {
            int level = 3;
            String levelName = "黄金";
            point.setLevel(level);
            point.setLevelName(levelName);
        } else {
            int level = 3;
            String levelName = "钻石";
            point.setLevel(level);
            point.setLevelName(levelName);
        }
        wxPointMapper.level(point);
        return BaseResult.success(point);
    }

}
