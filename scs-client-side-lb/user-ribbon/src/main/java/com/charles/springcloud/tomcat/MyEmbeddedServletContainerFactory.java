//package com.charles.springcloud;
//
//import org.apache.catalina.connector.Connector;
//import org.apache.coyote.http11.Http11NioProtocol;
//import org.springframework.boot.context.embedded.EmbeddedServletContainer;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyEmbeddedServletContainerFactory extends TomcatEmbeddedServletContainerFactory {
//    public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... initializers) {
//        //设置端口
//        this.setPort(8881);
//        return super.getEmbeddedServletContainer(initializers);
//    }
//
//    protected void customizeConnector(Connector connector) {
//        super.customizeConnector(connector);
//        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//        //设置最大连接数
//        protocol.setMaxConnections(8192);
//        //设置最大线程数
//        protocol.setMaxThreads(512);
//        protocol.setAcceptCount(1024);
//        protocol.setConnectionTimeout(30000);
//    }
//}