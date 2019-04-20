package com.charles.springcloud.tracing.sleuth.controller;

import com.charles.springcloud.tracing.sleuth.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sleuth")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/log1")
    public String log1(@RequestParam("content") String content) {
        return messageService.sendString(content);
    }

    @GetMapping("/log2")
    public String log2(@RequestParam("content") String content) {
        return messageService.sendObject(content);
    }
}
