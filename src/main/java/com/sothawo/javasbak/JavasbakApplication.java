package com.sothawo.javasbak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class JavasbakApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavasbakApplication.class, args);
    }
}
