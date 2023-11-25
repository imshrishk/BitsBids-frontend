package com.example.bitsbids3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@EntityScan(basePackages = "com.example.bitsbids3.entity")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Bitsbids3Application {

    public static void main(String[] args) {
        SpringApplication.run(Bitsbids3Application.class, args);
    }

}
