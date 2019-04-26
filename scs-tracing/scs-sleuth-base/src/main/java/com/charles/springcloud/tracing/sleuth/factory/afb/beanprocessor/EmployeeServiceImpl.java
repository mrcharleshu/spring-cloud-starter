package com.charles.springcloud.tracing.sleuth.factory.afb.beanprocessor;

import com.charles.springcloud.tracing.sleuth.factory.afb.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public Employee createNewEmployee() {
        Employee e = new Employee();
        e.setId(1);
        e.setFirstName("Lokesh");
        e.setLastName("Gupta");
        return e;
    }

    @PostConstruct
    public void initBean() {
        LOGGER.info("Init Bean for : EmployeeServiceImpl");
    }

    @PreDestroy
    public void destroyBean() {
        LOGGER.info("destroy Bean for : EmployeeServiceImpl");
    }
}
