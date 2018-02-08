package com.charles.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerRibbonController {
  @Autowired
  public RibbonService service;

    @RequestMapping(value = "/addInRibbon", method = RequestMethod.GET)
    public String add() {
        return service.sayHello();
    }

}
