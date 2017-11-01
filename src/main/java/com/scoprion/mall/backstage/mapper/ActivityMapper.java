package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * Created on 2017/11/1.
 */
@Mapper
public interface ActivityMapper {

    /**
     * 创建商品
     *
     * @param activity
     * @return
     */
    int add(Activity activity);

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 修改商品
     *
     * @param activity
     * @return
     */
    int update(Activity activity);

    /**
     * 条件查询商品
     *
     * @param searchKey
     * @return
     */
    Page<Activity> findByCondition(@Param("searchKey") String searchKey);

    /**
     * 校验活动名称是否存在
     *
     * @param name
     * @param id
     * @return
     */
    int validByName(Long id, String name);
}
