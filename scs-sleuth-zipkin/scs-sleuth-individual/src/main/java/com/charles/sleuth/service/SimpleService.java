package com.charles.sleuth.service;

import java.util.concurrent.Future;

public interface SimpleService {

    void testAsync1() throws InterruptedException;

    void testAsync2() throws InterruptedException;

    Future<String> testAsync3();

    void testParallelStream() throws InterruptedException;
}
