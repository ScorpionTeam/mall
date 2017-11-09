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
import java.util.ArrayList;
import java.util.List;

/**
 * @author ycj
 * @version V1.0 <文件上传控制器>
 * @date 2017-11-07 14:53
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {
    /**
     * 图片上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public BaseResult upload(@RequestParam MultipartFile file, String jsonContent) throws IOException {
        if (StringUtils.isEmpty(jsonContent)) {
            return BaseResult.parameterError();
        }
        jsonContent = jsonContent.replace("\r\n", "");
        JSONObject jsonObject = JSON.parseObject(jsonContent);
        String imageType = jsonObject.getString("imageType");
        String cut = jsonObject.getString("cut");
        String watermark = jsonObject.getString("watermark");
        List<ImageCutSize> cutSizeList;
        if (jsonObject.containsKey("cutSizeList")) {
            cutSizeList = jsonObject.getJSONArray("cutSizeList").toJavaList(ImageCutSize.class);
        } else {
            cutSizeList = new ArrayList<>();
        }
        if (StringUtils.isEmpty(imageType) || StringUtils.isEmpty(cut)) {
            return BaseResult.parameterError();
        }
        List<String> urlList = FileUploadUtils.uploadImage(file, imageType, cut, cutSizeList, watermark);
        return BaseResult.success(urlList);
    }

    /**
     * 图片删除
     *
     * @param imageName 图片名
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public BaseResult delete(String imageName) throws IOException {
        if (StringUtils.isEmpty(imageName)) {
            return BaseResult.parameterError();
        }
        FileUploadUtils.deleteImage(imageName);
        return BaseResult.success("删除成功");
    }

}
