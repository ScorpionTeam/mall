package com.scoprion.mall.backstage.controller;

import com.scoprion.annotation.Access;
import com.scoprion.mall.backstage.service.file.FileOperationService;
import com.scoprion.mall.domain.MallImage;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @Access
    @ApiOperation("图片上传")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public BaseResult uploadImage(@RequestParam MultipartFile file, String jsonContent) throws IOException {
        return fileOperationService.uploadImage(file, jsonContent);
    }

    @ApiOperation("图片上传test")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public BaseResult test(@RequestBody MallImage mallImage) {
        return fileOperationService.test(mallImage);
    }

    /**
     * 图片删除
     *
     * @param imageName 图片名
     * @return
     * @throws IOException
     */
    @Access
    @ApiOperation("图片删除")
    @RequestMapping(value = "/deleteImage", method = RequestMethod.GET)
    public BaseResult deleteImage(String imageName) {
        return fileOperationService.deleteImage(imageName);
    }

    /**
     * 图片列表
     *
     * @param pageNo
     * @param pageSize
     * @param type     商品图片0,品牌图片1,文章图片2,商品评价图片3,广告图片4,活动图片5,富文本图片6
     * @return
     */
    @Access
    @ApiOperation("图片列表")
    @RequestMapping(value = "/findByCondition", method = RequestMethod.GET)
    public PageResult findByCondition(Integer pageNo, Integer pageSize, Integer type) {
        return fileOperationService.findByCondition(pageNo, pageSize, type);
    }

}
