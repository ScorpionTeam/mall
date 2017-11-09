package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created on 2017/11/1.
 *
 * @author adming
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
     * @param id 商品ID、主键
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 修改商品
     *
     * @param activity
     * @return
     */
    int modify(Activity activity);

    /**
     * 条件查询h活动列表
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
    int validByName(@Param("name") String name);

    /**
     * 根据id 名称校验banner名称是否存在
     *
     * @param id
     * @param name
     * @return
     */
    int validByNameAndId(@Param("id") Long id, @Param("name") String name);


    /**
     * 添加活动商品匹配关系
     *
     * @param activityId
     * @param goodId
     */
    void addActivityGood(@Param("activityId") Long activityId, @Param("goodId") Long goodId);

    /**
     * 清空商品管理的活动
     *
     * @param goodId
     */
    void deleteActivityGood(@Param("goodId") Long goodId);

    /**
     * 根据商品id查询商品参加的活动
     *
     * @param goodId
     * @return
     */
    int findActivityByGoodId(@Param("goodId") Long goodId);

    /**
     * 根据ID查询活动详情
     *
     * @param id
     * @return
     */
    Activity findById(@Param("id") Long id);


    List<Long> findGoodIdByActivityId(@Param("activityId") Long activityId);

    /**
     * 批量修改活动状态
     *
     * @param status 状态 0正常 1删除
     * @param idList id集合
     * @return int
     */
    int batchModifyStatus(@Param("status") String status,@Param("idList") List<Long> idList);
}
