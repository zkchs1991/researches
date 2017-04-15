package com.zk.sbt.controller;

import com.zk.sbt.starter.log.autoconfigure.LogAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Qcon on 2017/4/9.
 */
@RestController
public class HelloController {

    @Autowired
    private SimpleService simpleService;

    @GetMapping(value = "/hello")
    public String hello (){
        simpleService.test(666);
        simpleService.core(666);
        simpleService.work(666);
        return "hello!";
    }

}
