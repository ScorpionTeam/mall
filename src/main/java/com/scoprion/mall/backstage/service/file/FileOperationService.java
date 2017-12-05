package com.scoprion.mall.backstage.service.file;

import com.scoprion.mall.domain.MallImage;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ycj
 * @version V1.0 <文件操作>
 * @date 2017-11-09 17:39
 */
@Service
public interface FileOperationService {
    /**
     * 图片上传
     *
     * @param jsonContent
     * @param file
     * @return
     * @throws IOException
     */
    BaseResult uploadImage(MultipartFile file, String jsonContent) throws IOException;

    /**
     * 图片删除
     *
     * @param imageName 图片名
     * @return
     * @throws IOException
     */
    BaseResult deleteImage(String imageName);

    /**
     * 图片列表
     *
     * @param pageNo
     * @param pageSize
     * @param type     商品图片0,品牌图片1,文章图片2,商品评价图片3,广告图片4,活动图片5,其他图片6
     * @return
     */
    PageResult findByCondition(Integer pageNo, Integer pageSize, Integer type);

    /**sss
      * @param mallImage
     * @return
     */
    BaseResult test(MallImage mallImage);
}
