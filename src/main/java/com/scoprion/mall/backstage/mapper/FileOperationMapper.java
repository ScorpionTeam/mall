package com.scoprion.mall.backstage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ycj
 * @version V1.0 <文件操作>
 * @date 2017-11-09 17:37
 */
@Mapper
public interface FileOperationMapper {

    /**
     * 删除图片
     *
     * @param imageName 图片名称
     * @return
     */
    int deleteImage(@Param("imageName") String imageName);
}
