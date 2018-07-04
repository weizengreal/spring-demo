package com.woai662.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.woai662")
public class Entry {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Entry.class,args);
    }
}
