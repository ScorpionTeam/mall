package com.scoprion.mall.wx.service.brand;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.domain.Brand;
import com.scoprion.mall.wx.mapper.WxBrandMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Service
public class WxBrandServiceImpl implements WxBrandService {
    @Autowired
    private WxBrandMapper wxBrandMapper;

    /**
     * 分页查询商标列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResult listByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        Page<Brand> page = wxBrandMapper.listByPage();
        return new PageResult(page);
    }
}
