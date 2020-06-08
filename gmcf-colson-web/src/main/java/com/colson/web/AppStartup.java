package com.colson.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:/spring/applicationContext.xml"})
@SpringBootApplication
public class AppStartup {

    public static void main(String[] args) {
            SpringApplication.run(AppStartup.class, args);
    }
}
