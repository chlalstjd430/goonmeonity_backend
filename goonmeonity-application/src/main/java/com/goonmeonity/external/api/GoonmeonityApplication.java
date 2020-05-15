package com.goonmeonity.external.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.goonmeonity")
@EntityScan(basePackages = "com.goonmeonity")
public class GoonmeonityApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoonmeonityApplication.class, args);
    }
}
