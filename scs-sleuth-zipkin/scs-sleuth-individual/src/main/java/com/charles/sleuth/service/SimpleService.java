package com.charles.sleuth.service;

public interface SimpleService {

    void testAsync1() throws InterruptedException;
    void testAsync2() throws InterruptedException;

    void testParallelStream() throws InterruptedException;
}
