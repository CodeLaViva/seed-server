package com.nullpointer.seed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author lihongzheng
 */
@SpringBootApplication()
@EnableJpaRepositories
public class SeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeedApplication.class, args);
    }

}
