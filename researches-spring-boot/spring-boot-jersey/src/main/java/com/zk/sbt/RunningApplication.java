package com.zk.sbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by zk_chs on 4/20/17.
 */
@SpringBootApplication
public class RunningApplication {

    public static void main(String[] args) {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        SpringApplication.run(RunningApplication.class, args);
    }

}
