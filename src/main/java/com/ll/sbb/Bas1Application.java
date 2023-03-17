package com.ll.sbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Bas1Application {

    public static void main(String[] args) {
        SpringApplication.run(Bas1Application.class, args);
    }

}
