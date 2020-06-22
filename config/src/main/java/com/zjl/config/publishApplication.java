package com.zjl.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zjl")
public class publishApplication {

    public static void main(String[] args) {

        SpringApplication.run(publishApplication.class, args);
    }

}