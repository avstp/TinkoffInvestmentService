package com.avstp.tinkoffinvestmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TinkoffInvestmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinkoffInvestmentServiceApplication.class, args);
    }

}
