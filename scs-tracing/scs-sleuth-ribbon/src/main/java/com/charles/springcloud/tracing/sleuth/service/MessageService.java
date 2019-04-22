package com.charles.springcloud.tracing.sleuth.service;

public interface MessageService {
    String sendString(String content);

    String sendObject(String content);
}
