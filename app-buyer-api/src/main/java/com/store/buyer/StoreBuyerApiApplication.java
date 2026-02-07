package com.store.buyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.store")
@EntityScan("com.store")
@EnableJpaRepositories("com.store")
public class StoreBuyerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreBuyerApiApplication.class, args);
    }
}
