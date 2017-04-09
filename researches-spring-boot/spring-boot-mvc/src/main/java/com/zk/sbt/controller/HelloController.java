package com.zk.sbt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Qcon on 2017/4/9.
 */
@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello (){
        return "hello!";
    }

}
