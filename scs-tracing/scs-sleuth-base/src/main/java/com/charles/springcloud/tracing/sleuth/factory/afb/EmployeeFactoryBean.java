package com.charles.springcloud.tracing.sleuth.factory.afb;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * https://howtodoinjava.com/spring-core/how-to-create-beans-using-spring-factorybean/
 * <p>
 * <pre>
 * <b>Factory beans are mostly used to implement framework facilities. Here are some examples:</b>
 *
 * 1. When looking up an object (such as a data source) from JNDI, you can use JndiObjectFactoryBean.
 * 2. When using classic Spring AOP to create a proxy for a bean, you can use ProxyFactoryBean.
 * 3. When creating a Hibernate session factory in the IoC container, you can use LocalSessionFactoryBean.
 * In most cases, you rarely have to write any custom factory beans, because they are framework-specific and cannot be used outside the scope of the Spring IoC container.
 * </pre>
 * <p>
 * “beanName” return the bean instance and “&beanName” returns the factory bean.
 */
public class EmployeeFactoryBean extends AbstractFactoryBean<Employee> {
    private String designation;

    public EmployeeFactoryBean(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    // This method will be called by container to create new instances
    @Override
    protected Employee createInstance() throws Exception {
        Employee employee = new Employee();
        employee.setId(-1);
        employee.setFirstName("dummy");
        employee.setLastName("dummy");
        // Set designation here
        employee.setDesignation(designation);
        return employee;
    }

    // This method is required for autowiring to work correctly
    @Override
    public Class<?> getObjectType() {
        return Employee.class;
    }
}