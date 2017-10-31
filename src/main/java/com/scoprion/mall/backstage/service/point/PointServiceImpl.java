package com.scoprion.mall.backstage.service.point;

import com.scoprion.mall.domain.Point;
import com.scoprion.mall.backstage.mapper.PointMapper;
import com.scoprion.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017/10/29.
 */
@Service
public class PointServiceImpl implements PointService {

    @Autowired
    private PointMapper pointMapper;

    /**
     * 查询我的积分
     *
     * @param userId
     * @return
     */
    @Override
    public BaseResult myPoint(Long userId) {
        Point point = pointMapper.myPoint(userId);
        if (null == point) {
            return BaseResult.notFound();
        }
        return BaseResult.success(point);
    }


}
