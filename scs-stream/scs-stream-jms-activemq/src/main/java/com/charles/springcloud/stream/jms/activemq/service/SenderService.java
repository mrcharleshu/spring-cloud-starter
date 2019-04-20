package com.charles.springcloud.stream.jms.activemq.service;

public interface SenderService {

    boolean send(String msg);

    boolean sendWithTags(String msg, String tag);

    <T> boolean sendObject(T msg);
}
