package com.scoprion.mall.backstage.controller;

import com.scoprion.mall.backstage.service.file.FileOperationService;
import com.scoprion.result.BaseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ycj
 * @version V1.0 <文件上传控制器>
 * @date 2017-11-07 14:53
 */
@RestController
@RequestMapping("/file")
public class FileOperationController {
    @Autowired
    FileOperationService fileOperationService;

    /**
     * 图片上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @ApiOperation("图片上传")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public BaseResult uploadImage(@RequestParam MultipartFile file, String jsonContent) throws IOException {
        return fileOperationService.uploadImage(file, jsonContent);
    }

    /**
     * 图片删除
     *
     * @param imageName 图片名
     * @return
     * @throws IOException
     */
    @ApiOperation("图片删除")
    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public BaseResult deleteImage(String imageName) throws IOException {
        return fileOperationService.deleteImage(imageName);
    }

}
