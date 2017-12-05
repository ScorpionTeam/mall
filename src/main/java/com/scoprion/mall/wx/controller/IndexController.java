package com.scoprion.mall.wx.controller;

import com.scoprion.annotation.ApiVersion;
import com.scoprion.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author by kunlun
 * @created on 2017/11/5.
 */
@RestController
@RequestMapping("/{version}/")
public class IndexController {

    @Value("${spring.datasource.url}")
    private String url;
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    DiscoveryClient discoveryClient;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiVersion(1)
    public BaseResult index() {
        LOGGER.info("版本号：V1");
        return BaseResult.success("V1");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiVersion(2)
    public BaseResult index1() {
        LOGGER.info("版本号：V2");
        return BaseResult.success("V2");
    }


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public BaseResult test(String wxCode) {
        return BaseResult.success(wxCode);
    }


    @GetMapping("/eureka-test")
    public String eureka() {
        String services = "Services:" + discoveryClient.getServices();
        LOGGER.info("打印参数：" + services);
        return services;
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
