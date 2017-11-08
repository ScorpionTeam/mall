package com.scoprion.mall.backstage.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scoprion.mall.domain.ImageCutSize;
import com.scoprion.result.BaseResult;
import com.scoprion.utils.FileUploadUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <文件上传控制器>
 * @date 2017-11-07 14:53
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    /**
     * 图片上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public BaseResult upload(@RequestParam MultipartFile file, String jsonContent) throws IOException {
        if (StringUtils.isEmpty(jsonContent)) {
            return BaseResult.parameterError();
        }
        jsonContent = jsonContent.replace("\r\n", "");
        JSONObject jsonObject = JSON.parseObject(jsonContent);
        String imageType = jsonObject.getString("imageType");
        String cut = jsonObject.getString("cut");
        String watermark = jsonObject.getString("watermark");
        String watermarkText = jsonObject.getString("watermarkText");
        List<ImageCutSize> cutSizeList = jsonObject.getJSONArray("cutSizeList").toJavaList(ImageCutSize.class);
        List<String> urlList = FileUploadUtils.upload(file, imageType, cut, cutSizeList, watermark, watermarkText);
        return BaseResult.success(urlList);
    }

}
