package com.money.transfer.controller;

import com.money.transfer.service.SayHiService;
import contracts.com.hello.world.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    SayHiService sayHiService;

    @GetMapping("/sayHi")
    public HelloWorld sayHiToTheBlockchain() throws Exception {
        return sayHiService.deployGreetingContract();
    }

}
