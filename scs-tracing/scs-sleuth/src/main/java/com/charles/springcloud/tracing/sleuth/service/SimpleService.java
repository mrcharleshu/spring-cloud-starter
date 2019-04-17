package com.charles.springcloud.tracing.sleuth.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface SimpleService {

    void testAsync1() throws InterruptedException;

    void testAsync2() throws InterruptedException;

    void testAsync3() throws ExecutionException, InterruptedException;

    CompletableFuture<String> asyncCompletableFuture() throws InterruptedException;

    void testParallelStream() throws InterruptedException;
}
