package com.scoprion.mall.littlesoft.mapper;

import com.scoprion.mall.domain.GoodSnapshot;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by admin1
 * @created on 2017/11/2.
 */
@Mapper
public interface GoodSnapShotWxMapper {

    /**
     * 新增快照
     *
     * @param goodSnapshot
     * @return
     */
    Integer add(GoodSnapshot goodSnapshot);
}
