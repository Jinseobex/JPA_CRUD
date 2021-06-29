package com.zerock.jpatests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpAtestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpAtestsApplication.class, args);
    }

}
