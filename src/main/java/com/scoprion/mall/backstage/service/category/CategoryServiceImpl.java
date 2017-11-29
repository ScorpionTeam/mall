package com.scoprion.mall.backstage.service.category;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.mall.backstage.mapper.CategoryGoodMapper;
import com.scoprion.mall.backstage.mapper.CategoryMapper;
import com.scoprion.mall.domain.Category;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author ycj
 * @version V1.0 <类目>
 * @date 2017-11-29 10:58
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryGoodMapper categoryGoodMapper;

    @Override
    public BaseResult add(Category category) {
        if (category.getCategoryName() == null) {
            return BaseResult.error("add_error", "类目名称不能为空");
        }
        int count = categoryMapper.validByName(category.getCategoryName());
        if (count > 0) {
            return BaseResult.error("add_error", "已经存在相同名字的类目");
        }
        int result = categoryMapper.add(category);
        if (result > 0) {
            return BaseResult.success("添加成功");
        }
        return BaseResult.error("add_error", "添加失败");
    }

    @Override
    public BaseResult modify(Category category) {
        if (category.getId() == null) {
            return BaseResult.error("modify_error", "id不能为空");
        }
        int count = categoryMapper.validByNameAndId(category.getId(), category.getCategoryName());
        if (count > 0) {
            return BaseResult.error("add_error", "已经存在相同名字的类目");
        }
        categoryMapper.modify(category);
        return BaseResult.success("修改成功");
    }

    @Override
    public BaseResult deleteById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Category category = categoryMapper.findById(id);
        int count = categoryMapper.deleteById(id);
        if (count > 0 && category.getId() != 0) {
            //子类目，清空绑定关系
            categoryGoodMapper.unbindWithCategoryId(id);
            return BaseResult.success("删除成功");
        }
        return BaseResult.systemError();
    }

    @Override
    public BaseResult findById(Long id) {
        if (id == null) {
            return BaseResult.parameterError();
        }
        Category category = categoryMapper.findById(id);
        if (category == null) {
            return BaseResult.notFound();
        }
        return BaseResult.success(category);
    }

    /**
     * 列表查询
     *
     * @param pageNo    页码
     * @param pageSize  数量
     * @param searchKey 模糊查询信息
     * @param type      一级类目PARENT  CHILD
     * @return
     */
    @Override
    public PageResult findByCondition(Integer pageNo, Integer pageSize, String type, String searchKey) {
        PageHelper.startPage(pageNo, pageSize);
        if (StringUtils.isEmpty(type)) {
            type = null;
        }
        if (StringUtils.isEmpty(searchKey)) {
            searchKey = null;
        }
        if (searchKey != null) {
            searchKey = "%" + searchKey + "%";
        }
        Page<Category> page = categoryMapper.findByCondition(searchKey, type);
        return new PageResult(page);
    }

    @Override
    public BaseResult modifyStatus(String status, Long id) {
        if (id == null || StringUtils.isEmpty(status)) {
            return BaseResult.parameterError();
        }
        categoryMapper.modifyStatus(status, id);
        return BaseResult.success("修改成功");
    }

    /**
     * 商品解绑类目
     *
     * @param categoryId 类目 id
     * @param goodIdList 商品 id
     * @return
     */
    @Override
    public BaseResult unbindCategoryGood(Long categoryId, List<Long> goodIdList) {
        Integer result = categoryGoodMapper.unbindCategoryGood(categoryId, goodIdList);
        if (result > 0) {
            return BaseResult.success("解绑成功");
        }
        return BaseResult.systemError();
    }

    /**
     * 商品绑定类目
     *
     * @param categoryId 类目 id
     * @param goodIdList 商品 id
     * @return
     */
    @Override
    public BaseResult bindCategoryGood(Long categoryId, List<Long> goodIdList) {
        goodIdList.forEach(goodId -> {
            categoryGoodMapper.bindCategoryGood(categoryId, goodId);
        });
        return BaseResult.success("绑定成功");
    }
}
