package com.charles.springcloud.stream.producer.service;

public interface SenderService {

    boolean sendString(String msg);

    boolean sendWithTags(String msg, String tag);

    <T> boolean sendObject(T msg);
}
