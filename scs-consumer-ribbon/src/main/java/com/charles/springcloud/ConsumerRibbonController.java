package com.charles.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/ribbon")
@RestController
public class ConsumerRibbonController {
    @Autowired
    public RibbonClientService ribbonClientService;

    @GetMapping("/hello")
    public String sayHello() {
        log.info("Request helloRibbon......");
        return ribbonClientService.sayHello();
    }
}
