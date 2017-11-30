package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.Access;
import com.scoprion.annotation.AccessSecret;
import com.scoprion.enums.CommonEnum;
import com.scoprion.result.BaseResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

/**
 * @author by kunlun
 * @created on 2017/11/5.
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @Value("${spring.datasource.url}")
    private String url;
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Access
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public BaseResult index() {

        LOGGER.info("进入......");
        LOGGER.info(url);
        return BaseResult.success("Hello world");
    }


    @Access(need = false)
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public BaseResult test(String world) {
        return BaseResult.success(world);
    }


    public static void main(String[] args) {
        byte[] bytes = CommonEnum.AUTH.getDesc().getBytes(Charset.forName("UTF-8"));
        byte[] md5 = DigestUtils.sha1(bytes);
        System.out.println(DigestUtils.md5(md5));
    }
}
