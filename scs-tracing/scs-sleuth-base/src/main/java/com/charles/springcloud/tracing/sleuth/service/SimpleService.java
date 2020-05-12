package com.charles.springcloud.tracing.sleuth.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SimpleService {
    List<String> TEST_NAMES = Arrays.asList("Tom", "Jerry", "Mary", "Colin");

    void testNewSpanMethod(String value);

    void testAsync1() throws InterruptedException;

    void testAsync2() throws InterruptedException;

    void testAsync3() throws ExecutionException, InterruptedException;

    void testAsync4() throws ExecutionException, InterruptedException;

    void testParallelStream() throws InterruptedException;
}
