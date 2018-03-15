package com.charles.springcloud.supplier.db.sharding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.TimeZone;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableJpaAuditing
@EntityScan(basePackageClasses = {ShardingApplication.class, Jsr310JpaConverters.class})
public class ShardingApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShardingApplication.class);

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(ShardingApplication.class, args);
        //ApplicationContext ctx = SpringApplication.run(ShardingApplication.class, args);
        //printBeansName(ctx);
    }

    private static void printBeansName(ApplicationContext ctx) {
        LOGGER.debug("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOGGER.info(beanName);
        }
    }
}
