package com.krizhanovsky.springbootrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-api")
public class MyRestController {

    @GetMapping("say-hello")
    public String sayHello(@RequestParam(required = false,defaultValue = "World!") String name){
        return "Hello, " + name;
    }

}
