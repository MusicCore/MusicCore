package com.wjk.sstm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@PropertySource("${spring.config.location:classpath}:common-application.properties")
public class SstmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SstmApplication.class, args);
    }

}

