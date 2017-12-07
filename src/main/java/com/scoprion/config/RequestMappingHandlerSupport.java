//package com.scoprion.config;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.scoprion.constant.Constant;
//import com.scoprion.intercepter.MallInterceptor;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.util.List;
//
///**
// * @author by kunlun
// * @created on 2017/12/5.
// */
//@Configuration
//public class RequestMappingHandlerSupport extends WebMvcConfigurationSupport {
//
//    @Bean
//    public MallInterceptor getMallInterceptor() {
//        return new MallInterceptor();
//    }
//
//    /**
//     * 注入拦截器
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getMallInterceptor());
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastJsonHttpMessageConverter);
//        super.configureMessageConverters(converters);
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        super.addResourceHandlers(registry);
//    }
//
//
//    @Override
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping handlerMapping = new MallRequestMappingHandler();
//        handlerMapping.setOrder(0);
//        handlerMapping.setInterceptors(getInterceptors());
//        return handlerMapping;
//    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**/**")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .allowedMethods("*");
//        super.addCorsMappings(registry);
//    }
//
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory factory = new CachingConnectionFactory();
//        factory.setAddresses("127.0.0.1:5672");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        //回调必须设置  否则接收不到
//        factory.setPublisherConfirms(true);
//        factory.setPublisherReturns(true);
//        //设置连接数  此种方式已解决高并发数据丢失 重连丢失问题
//        factory.setChannelCacheSize(100);
//        return factory;
//    }
//    @Bean
//    public DirectExchange defaultExchange() {
//
//        /**
//         * DirectExchange: 按照routingkey分发到指定队列
//         * TopicExchange: 多关键字匹配
//         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
//         * HeadersExchange: 通过添加属性 key-value匹配
//         */
//        return new DirectExchange(Constant.EXCHANGE);
//    }
//
//
//    @Bean
//    public Queue queue() {
//        return new Queue(Constant.QUEUE);
//    }
//
//    @Bean
//    public Binding binding() {
//        /** 将队列绑定到交换机 */
//        return BindingBuilder.bind(queue()).to(defaultExchange()).with(Constant.ROUTING_KEY);
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }
//
//}
