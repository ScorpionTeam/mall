package com.scoprion.mall.wx.controller;

import com.alibaba.fastjson.annotation.JSONField;
import com.scoprion.annotation.Access;
import com.scoprion.annotation.AccessSecret;
import com.scoprion.enums.CommonEnum;
import com.scoprion.result.BaseResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.ServletContextResource;

import java.io.IOException;
import java.io.InputStream;
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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public BaseResult index() {

        LOGGER.info("进入......");
        LOGGER.info(url);
        return BaseResult.success("Hello world");
    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public BaseResult test(String wxCode) {
        return BaseResult.success(wxCode);
    }


//    public static void main(String[] args) throws IOException {
//        String path = "/Users/kunlun/Desktop/mall/src/main/resources/application.yml";
//
//        //使用系统路径
//        Resource resource = new FileSystemResource(path);
//
//        //使用类路径
//        Resource resource1 = new ClassPathResource("resources/application.yml");
//
//        System.out.println(resource.getFilename());
//
//        System.out.println(resource1.getFilename());
//
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource resource2[] = resolver.getResources("classpath:*.*");
//        for (Resource resource3 : resource2) {
//            System.out.println(resource3.getDescription());
//        }
//    }
}
