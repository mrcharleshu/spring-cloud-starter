//package com.charles.springcloud;
//
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebServerConfiguration1 {
//    @Bean
//    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
//        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
//        tomcatFactory.setPort(8888);
//        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
//        return tomcatFactory;
//    }
//}
