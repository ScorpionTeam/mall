package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * Created on 2017/11/1.
 *
 * @author adming
 */
@Mapper
public interface ActivityMapper {

    /**
     * 创建活动
     *
     * @param activity
     * @return
     */
    Integer add(Activity activity);

    /**
     * 删除活动
     *
     * @param id 商品ID、主键
     * @return
     */
    Integer deleteById(@Param("id") Long id);

    /**
     * 修改活动
     *
     * @param activity
     * @return
     */
    Integer modify(Activity activity);

    /**
     * 条件查询活动列表
     *
     * @param type
     * @param status
     * @param searchKey
     * @return
     */
    Page<Activity> findByCondition(@Param("type") String type,
                                   @Param("status") String status,
                                   @Param("searchKey") String searchKey);

    /**
     * 校验活动名称是否存在
     *
     * @param name
     * @return
     */
    Integer validByName(@Param("name") String name);

    /**
     * 根据id 名称校验banner名称是否存在
     *
     * @param id
     * @param name
     * @return
     */
    Integer validByNameAndId(@Param("id") Long id,
                             @Param("name") String name);


    /**
     * 根据ID查询活动详情
     *
     * @param id
     * @return
     */
    Activity findById(@Param("id") Long id);

    /**
     * 批量修改活动状态
     *
     * @param status 状态 0正常 1删除
     * @param idList id集合
     * @return int
     */
    Integer batchModifyStatus(@Param("status") String status,
                              @Param("idList") List<Long> idList);

    /**
     * 校验相同时间内是否存在相同类型的活动
     *
     * @param startDate
     * @param endDate
     * @param activityType
     * @return
     */
    Integer validByTypeAndTime(@Param("startDate") Date startDate,
                           @Param("endDate") Date endDate,
                           @Param("activityType") String activityType);
}
