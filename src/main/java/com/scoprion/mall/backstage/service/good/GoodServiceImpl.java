package com.scoprion.mall.backstage.service.good;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.backstage.mapper.GoodMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2017/9/29.
 */
@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;


    /**
     * 首页展示4件 限时购买商品
     *
     * @return
     */
    @Override
    public List<Good> findLimit4ByTimeGoods() {
        return goodMapper.findLimit4ByTimeGoods();
    }

    /**
     * 查询限时购买商品  分页展示
     *
     * @param pageNo   当前页
     * @param pageSize 每页条数
     * @return
     */
    @Override
    public PageResult findByPageAndLimit(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Good> page = goodMapper.findByPageAndLimit();
        return new PageResult(page);
    }

    /**
     * 创建商品
     *
     * @param good
     * @return
     */
    @Override
    public BaseResult add(Good good) {
        int result = goodMapper.add(good);
        if (result > 0) {
            return BaseResult.success("创建模拟商品成功....");
        }
        return BaseResult.error("mock_fail", "创建模拟商品失败....");
    }

    /**
     * 优选商品
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult preferenceGiven(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Good> page = goodMapper.preferenceGivenByPage();
        return new PageResult(page);
    }

    /**
     * 根据id查询商品详情
     *
     * @param goodId
     * @return
     */
    @Override
    public BaseResult goodInfo(Long goodId) {
        Good good = goodMapper.findById(goodId);
        if (null == good) {
            return BaseResult.notFound();
        }
        return BaseResult.success(good);
    }

    /**
     * 根据id修改商品信息
     *
     * @param good
     * @return
     */
    @Override
    public BaseResult update(Good good) {
        return null;
    }

}
