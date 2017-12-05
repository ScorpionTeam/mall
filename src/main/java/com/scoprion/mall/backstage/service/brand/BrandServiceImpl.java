package com.scoprion.mall.backstage.service.brand;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.constant.Constant;
import com.scoprion.mall.backstage.mapper.BrandMapper;
import com.scoprion.mall.backstage.mapper.FileOperationMapper;
import com.scoprion.mall.domain.Brand;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <品牌>
 * @date 2017-11-08 15:48
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    FileOperationMapper fileOperationMapper;

    /**
     * 增加品牌
     *
     * @param brand Brand
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult add(Brand brand) {
        if (brand.getBrandName() == null) {
            return BaseResult.error("ERROR", "品牌名称不能为空");
        }
        int count = brandMapper.validByName(brand.getBrandName());
        if (count > 0) {
            return BaseResult.error("ERROR", "已经存在相同名字的品牌");
        }
        int result = brandMapper.add(brand);
        if (result > 0) {
            if (!StringUtils.isEmpty(brand.getBrandImage())) {
                MallImage mallImage = new MallImage();
                mallImage.setUrl(brand.getBrandImage());
                mallImage.setBrandId(brand.getId());
                fileOperationMapper.add(mallImage);
            }
            return BaseResult.success("增加成功");
        }
        return BaseResult.error("ERROR", "增加失败");
    }

    /**
     * 修改品牌
     *
     * @param brand Brand
     * @return
     */
    @Override
    public BaseResult modify(Brand brand) {
        if (brand.getId() == null) {
            return BaseResult.error("ERROR", "id不能为空");
        }
        int count = brandMapper.validByNameAndId(brand.getId(), brand.getBrandName());
        if (count > 0) {
            return BaseResult.error("ERROR", "已经存在相同名字的品牌");
        }
        brandMapper.modify(brand);
        return BaseResult.success("修改成功");
    }

    /**
     * 批量删除品牌
     *
     * @param idList List<Long>
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public BaseResult batchDelete(List<Long> idList) {
        if (idList == null || idList.size() == 0) {
            return BaseResult.parameterError();
        }
        int count = brandMapper.batchDelete(idList);
        if (count > 0) {
            return BaseResult.success("批量删除成功");
        }
        return BaseResult.systemError();
    }

    /**
     * 根据ID查询详情
     *
     * @param id Long
     * @return
     */
    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Brand brand = brandMapper.findById(id);
        if (brand == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(brand);
    }

    /**
     * 列表查询
     *
     * @param pageNo    页码
     * @param pageSize  数量
     * @param searchKey 模糊查询信息
     * @return
     */
    @Override
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (searchKey != null) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Brand> page = brandMapper.findByCondition(searchKey);
        if (page.getTotal() <= 0L) {
            return new PageResult(new ArrayList());
        }
        return new PageResult(page);
    }

    /**
     * 批量修改品牌状态
     *
     * @param status 状态 1入驻 0 退出 2删除 状态 ENTER入驻 QUITE退出
     * @param idList id集合
     * @return
     */
    @Override
    public BaseResult batchModifyStatus(String status, List<Long> idList) {
        if (idList == null || idList.size() == 0 || StringUtils.isEmpty(status)) {
            return BaseResult.parameterError();
        }
        brandMapper.batchModifyStatus(status, idList);
        return BaseResult.success("修改成功");
    }
}
