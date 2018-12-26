package com.wjk.sstm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("${spring.config.location:classpath}:common-application.properties")
public class SstmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SstmApplication.class, args);
    }

}

