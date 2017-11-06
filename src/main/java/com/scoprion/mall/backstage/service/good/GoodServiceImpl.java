package com.scoprion.mall.backstage.service.good;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.domain.Good;
import com.scoprion.mall.backstage.mapper.GoodMapper;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMStringList;

import java.util.List;

/**
 * Created on 2017/9/29.
 * 运营后台商品控制器
 *
 * @author adming
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
            return BaseResult.success("创建商品成功....");
        }
        return BaseResult.error("mock_fail", "创建商品失败....");
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
    public Good findByGoodId(Long goodId) {
        Good good = goodMapper.findById(goodId);
        if (null == good) {
            return null;
        }
        return good;
    }

    /**
     * 根据id修改商品信息
     *
     * @param good Good
     * @return
     */
    @Override
    public BaseResult update(Good good) {
        int result = goodMapper.updateGood(good);
        if (result > 0) {
            return BaseResult.success("更新成功");
        }
        return BaseResult.success("更新失败");
    }

    /**
     * 条件查询商品列表分页
     *
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    @Override
    public PageResult findByCondition(int pageNo, int pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (!StringUtils.isEmpty(searchKey)) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Good> page = goodMapper.findByCondition(searchKey);
        return new PageResult(page);
    }

    /**
     * 商品上下架
     *
     * @param saleStatus saleStatus 1上架 0下架 默认上架
     * @param goodId     商品id
     * @return
     */
    @Override
    public BaseResult modifySaleStatus(String saleStatus, Long goodId) {
        if (StringUtils.isEmpty(saleStatus) || null == goodId) {
            return BaseResult.parameterError();
        }
        if (!Constant.SALE_STATUS.contains(saleStatus)) {
            return BaseResult.error("parameterError", "上下架状态不正确");
        }
        int result = goodMapper.modifySaleStatus(saleStatus, goodId);
        if (result > 0) {
            return BaseResult.success(Constant.ON_SALE.equals(saleStatus) ? "商品上架成功" : "商品下架成功");
        }
        return BaseResult.error("006", Constant.ON_SALE.equals(saleStatus) ? "商品上架失败" : "商品下架失败");
    }

    /**
     * 根据商品id删除商品
     *
     * @param id 商品id
     * @return
     */
    @Override
    public BaseResult deleteGoodById(Long id) {
        int result = goodMapper.deleteGoodById(id);
        if (result > 0) {
            return BaseResult.success("删除商品成功");
        }
        return BaseResult.error("sysError", "删除商品失败");
    }
}
