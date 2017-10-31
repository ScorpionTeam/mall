package com.scoprion.mall.backstage.mapper;

import com.scoprion.mall.domain.GoodSnapshot;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2017/10/14.
 */
@Mapper
public interface GoodSnapShotMapper {

    /**
     * 生成商品快照
     *
     * @param goodSnapshot
     * @return
     */
    int add(GoodSnapshot goodSnapshot);

    /**
     * 根据主键查询商品快照信息
     *
     * @param id
     * @return
     */
    GoodSnapshot findById(Long id);


}
