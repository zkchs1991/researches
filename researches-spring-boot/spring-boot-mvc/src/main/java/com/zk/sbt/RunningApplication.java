package com.zk.sbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Qcon on 2017/4/9.
 */
@SpringBootApplication
public class RunningApplication {

    public static void main(String[] args) {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        SpringApplication.run(RunningApplication.class, args);
    }

}
