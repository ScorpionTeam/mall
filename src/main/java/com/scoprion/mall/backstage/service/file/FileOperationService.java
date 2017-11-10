package com.scoprion.mall.backstage.service.file;

import com.scoprion.result.BaseResult;
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
    BaseResult deleteImage(String imageName) throws IOException;
}
