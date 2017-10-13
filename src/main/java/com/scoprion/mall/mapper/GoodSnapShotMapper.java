package com.scoprion.mall.mapper;

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


}
