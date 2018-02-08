//package com.ribbon;
//
//import feign.Request;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * feign的配置除了以下的配置还不够
// * <pre>
// * feign.hystrix.enabled=true
// * hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=15000
// * </pre>
// * 除了可以用本Configuration的方法设置timeout之外还可以在配置文件中加上以下
// * <pre>
// * ribbon.ConnectTimeout=15000
// * ribbon.ReadTimeout=15000
// * </pre>
// */
//@Configuration
//public class FeignConfigure {
//    //    private static final int connectTimeOutMillis = 12000;
//    //    private static final int readTimeOutMillis = 12000;
//    @Value(value = "${feign.connectTimeOutMillis}")
//    private int connectTimeOutMillis = 12000;
//    @Value(value = "${feign.readTimeOutMillis}")
//    private int readTimeOutMillis = 12000;
//
//    @Bean
//    public Request.Options options() {
//        System.out.println(String.format("connectTimeOutMillis:%s |readTimeOutMillis:%s ",
//                connectTimeOutMillis, readTimeOutMillis));
//        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
//    }
//}