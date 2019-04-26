package com.charles.springcloud.tracing.sleuth.factory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class FactoryRunner implements CommandLineRunner, Ordered {
    private final FactoryBeanService factoryBeanService;

    public FactoryRunner(FactoryBeanService factoryBeanService) {
        this.factoryBeanService = factoryBeanService;
    }

    @Override
    public void run(String... args) throws Exception {
        factoryBeanService.test();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
