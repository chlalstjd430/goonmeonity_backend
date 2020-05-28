package com.goonmeonity.external.api;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.goonmeonity")
@EntityScan(basePackages = "com.goonmeonity")
@EnableJpaRepositories(basePackages ="com.goonmeonity")
public class GoonmeonityApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoonmeonityApplication.class, args);
    }
}
