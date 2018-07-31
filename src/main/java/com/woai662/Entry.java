package com.woai662.demo.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration
//@EnableDiscoveryClient
//@ComponentScan(basePackages = "com.woai662.demo")
public class Entry {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Entry.class,args);
    }
}
