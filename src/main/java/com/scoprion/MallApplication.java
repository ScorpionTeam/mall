package com.scoprion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author by kunlun
 * @created on 2017/11/6
 */
@SpringBootApplication
@EnableScheduling
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainerFactory() {
//        JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
//        return factory;
//    }
}
