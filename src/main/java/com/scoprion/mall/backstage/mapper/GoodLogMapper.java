package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.good.GoodLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2017/9/29.
 *
 * @author adming
 */
@Mapper
public interface GoodLogMapper {
    /**
     * 添加
     *
     * @param goodLog
     * @return
     */
    Integer add(GoodLog goodLog);

    /**
     * 分页模糊查询
     *
     * @param searchKey
     * @param goodId
     * @return
     */
    Page<GoodLog> findByCondition(@Param("goodId") Long goodId,
                                  @Param("searchKey") String searchKey);

}
