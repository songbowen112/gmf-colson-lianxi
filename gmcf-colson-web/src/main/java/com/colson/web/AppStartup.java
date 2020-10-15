package com.colson.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:/spring/applicationContext.xml","classpath:/spring/applicationContext-service.xml","classpath:/spring/applicationContext-dal.xml"})
@ComponentScan(basePackages = {"com.colson"})
@SpringBootApplication
public class AppStartup {

    public static void main(String[] args) {
            SpringApplication.run(AppStartup.class, args);
    }
}
