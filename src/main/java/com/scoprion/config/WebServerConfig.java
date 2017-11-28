package com.scoprion.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by kunlun
 * @created on 2017/11/28.
 */
@Configuration
public class WebServerConfig {

    @Bean
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(8088);
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomer());
        return tomcatFactory;
    }
}

class MyTomcatConnectorCustomer implements TomcatConnectorCustomizer{
    @Override
    public void customize(Connector connector) {
        Http11NioProtocol protocol = (Http11NioProtocol)connector.getProtocolHandler();
        protocol.setMaxConnections(2000);
        protocol.setMaxThreads(2000);
        protocol.setConnectionTimeout(30000);
        protocol.setMaxHttpHeaderSize(8192);
    }
}
