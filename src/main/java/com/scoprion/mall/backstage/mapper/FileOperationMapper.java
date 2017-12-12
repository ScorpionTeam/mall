package com.scoprion.mall.backstage.mapper;

import com.github.pagehelper.Page;
import com.scoprion.mall.domain.MallImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ycj
 * @version V1.0 <文件操作>
 * @date 2017-11-09 17:37
 */
@Mapper
public interface FileOperationMapper {
    /**
     * 根据商品id删除图片
     *
     * @param id
     * @return
     */
    int deleteById(@Param("id") Long id);

    /**
     * 删除图片
     *
     * @param url 图片链接
     * @return
     */
    int deleteByUrl(@Param("url") String url);

    /**
     * 图片对应的商品ID
     *
     * @param mallImage
     * @return
     */
    int modify(MallImage mallImage);

    /**
     * 增加图片
     *
     * @param mallImage
     * @return
     */
    int add(MallImage mallImage);

    /**
     * 根据商品ID查找图片列表
     *
     * @param type 类型 商品图片0,品牌图片1,文章图片2,商品评价图片3,广告图片4,活动图片5,
     *             富文本图片路径 6 ,证件照图片路径  7
     * @return
     */
    Page<MallImage> findByCondition(@Param("type") Integer type);

    /**
     * 根据商品ID查找图片列表
     *
     * @param targetId
     * @param type     类型 商品图片0,品牌图片1,文章图片2,商品评价图片3,广告图片4,活动图片5,
     *                 富文本图片路径 6 ,证件照图片路径  7
     * @return
     */
    List<MallImage> findByTargetId(@Param("targetId") Long targetId, @Param("type") Integer type);
}
