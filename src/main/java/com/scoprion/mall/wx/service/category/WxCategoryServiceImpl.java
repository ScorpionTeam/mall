package com.scoprion.mall.wx.service.category;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scoprion.enums.CommonEnum;
import com.scoprion.mall.domain.Category;
import com.scoprion.mall.domain.CategoryExt;
import com.scoprion.mall.wx.mapper.WxCategoryMapper;
import com.scoprion.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ycj
 * @version V1.0 <>
 * @date 2017-11-30 09:51
 */
@Service
public class WxCategoryServiceImpl implements WxCategoryService {

    @Autowired
    WxCategoryMapper wxCategoryMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageResult findHomePage(Integer pageNo, Integer pageSize, String type) {
        PageHelper.startPage(pageNo, pageSize);
        if (CommonEnum.PARENT_CATEGORY.getCode().equals(type)) {
            Page<CategoryExt> page = wxCategoryMapper.findParentPage();
            return new PageResult(page);
        } else if (CommonEnum.CHILD_CATEGORY.getCode().equals(type)) {
            Page<Category> page = wxCategoryMapper.findChildPage(null);
            return new PageResult(page);
        } else {
            Page<CategoryExt> page = wxCategoryMapper.findParentPage();
            page.forEach(item -> {
                Page<Category> childCategory = wxCategoryMapper.findChildPage(item.getId());
                item.setChildList(childCategory);
            });
            return new PageResult(page);
        }

    }
}
