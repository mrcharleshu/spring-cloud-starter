//package com.charles.springcloud;
//
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WebServerConfiguration2 {
//    @Bean
//    public EmbeddedServletContainerCustomizer createEmbeddedServletContainerCustomizer() {
//        return new MyEmbeddedServletContainerCustomizer();
//    }
//
//    private static class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {
//        public void customize(ConfigurableEmbeddedServletContainer container) {
//            TomcatEmbeddedServletContainerFactory tomcatFactory = (TomcatEmbeddedServletContainerFactory) container;
//            tomcatFactory.setPort(8888);
//            tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
//        }
//    }
//}
