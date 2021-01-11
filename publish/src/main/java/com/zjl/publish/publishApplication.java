package com.zjl.publish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.zjl")
@EnableScheduling  //定时任务
public class publishApplication {

    public static void main(String[] args) {
        SpringApplication.run(publishApplication.class, args);
    }

}