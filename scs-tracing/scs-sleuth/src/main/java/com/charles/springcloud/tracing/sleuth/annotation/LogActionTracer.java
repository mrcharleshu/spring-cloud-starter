package com.charles.springcloud.tracing.sleuth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogActionTracer {

    /**
     * API的业务操作
     * @return
     */
    String action() default "";

    /**
     * 改次操作的操作人
     * @return
     */
    String operator() default "";

    /**
     * 改API是否未完成，需要另一个API协助一起完成一个操作
     * @return
     */
    boolean continued() default false;

    /**
     * 如果@LogActionTracer.reenterable为true，需要设置reentrantKey
     * @return
     */
    ReentrantType reentrantKey() default ReentrantType.SERIAL_NUMBER;
}