package com.charles.springcloud.tracing.service;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {
    ListenableFuture<String> getListenableFuture() throws InterruptedException;

    CompletableFuture<String> getCompletableFuture() throws InterruptedException;

    void simpleAsyncPrint(String str) throws InterruptedException;
}
