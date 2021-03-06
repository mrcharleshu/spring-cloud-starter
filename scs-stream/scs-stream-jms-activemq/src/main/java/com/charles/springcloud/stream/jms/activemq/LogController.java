package com.charles.springcloud.stream.jms.activemq;

import com.charles.springcloud.stream.Log;
import com.charles.springcloud.stream.jms.activemq.service.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class LogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);
    private Random random = new Random();
    private final SenderService senderService;

    @Autowired
    public LogController(SenderService senderService) {
        this.senderService = senderService;
    }

    @GetMapping("/log1")
    public String sendLog1(@RequestParam("content") String content) {
        boolean isSendSuccess = senderService.send(content);
        LOGGER.info(isSendSuccess ? "发送成功" : "发送失败");
        return isSendSuccess ? "发送成功" : "发送失败";
    }

    @GetMapping("/log2")
    public String sendLog2(@RequestParam("content") String content) {
        boolean isSendSuccess = senderService.sendObject(new Log(random.nextInt(), content));
        LOGGER.info(isSendSuccess ? "发送成功" : "发送失败");
        return isSendSuccess ? "发送成功" : "发送失败";
    }
}