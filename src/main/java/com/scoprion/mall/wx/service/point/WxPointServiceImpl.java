package com.scoprion.mall.wx.service.point;

import com.scoprion.mall.domain.Order;
import com.scoprion.mall.domain.Point;
import com.scoprion.mall.domain.PointLog;
import com.scoprion.mall.wx.mapper.WxOrderMapper;
import com.scoprion.mall.wx.mapper.WxPointLogMapper;
import com.scoprion.mall.wx.mapper.WxPointMapper;
import com.scoprion.result.BaseResult;
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
        userId = pointLog.getUserId();
        String action=wxPointLogMapper.personalScore(id, userId).toString();
        if(action=="0"){
            if (currentPoint >= score) {
                currentPoint = currentPoint - score;
            } else {
                currentPoint = 0;
            }
        }else {
            currentPoint=currentPoint+score;
        }

        wxPointLogMapper.personal(userId,currentPoint);
        return BaseResult.success("操作成功");
    }
}
