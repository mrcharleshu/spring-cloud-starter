package com.charles.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerRibbonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerRibbonController.class);
    @Autowired
    public RibbonService ribbonService;

    @RequestMapping(value = "/addInRibbon", method = RequestMethod.GET)
    public String sayHello() {
        LOGGER.info("Request addInRibbon......");
        return ribbonService.sayHello();
    }

}
