package com.scoprion.mall.wx.service.point;


import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Point;
import com.scoprion.mall.domain.PointLog;
import com.scoprion.mall.wx.mapper.WxPointLogMapper;
import com.scoprion.mall.wx.mapper.WxPointMapper;
import com.scoprion.mall.wx.pay.util.WxUtil;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/**
 * @author by kunlun
 * @created on 2017/11/8.
 */
@SuppressWarnings("ALL")
@Service
public class WxPointServiceImpl implements WxPointService {

    @Autowired
    private WxPointMapper wxPointMapper;

    @Autowired
    private WxPointLogMapper wxPointLogMapper;


    /**
     * 根据用户id查询用户信息
     *
     * @param wxCode
     * @return
     */
    @Override
    public BaseResult findByUserId(String wxCode) {
        String userId = WxUtil.getOpenId(wxCode);
        if (StringUtils.isEmpty(userId)) {
            return BaseResult.parameterError();
        }
        Point point = wxPointMapper.findByUserId(userId);
        if (point == null) {
            point = new Point();
            point.setPoint(0);
            point.setLevel(0);
            point.setLevelName("白银");
            return BaseResult.success(point);
        }
        return BaseResult.success(point);
    }


    /**
     * 积分记录
     *
     * @param pageNo
     * @param pageSize
     * @param wxCode
     * @return
     */
    @Override
    public PageResult pointLog(Integer pageNo, Integer pageSize, String wxCode) {
        String userId = WxUtil.getOpenId(wxCode);
        PageHelper.startPage(pageNo, pageSize);
        Page<PointLog> pointLogs = wxPointLogMapper.findLogPage(userId);
        return new PageResult(pointLogs);
    }

}
