package com.raro28.payments.api.rest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.openpay.client.core.OpenpayAPI;

@Configuration
public class OpenPayConfiguration {

    @Bean
    public OpenpayAPI api(){
        return new OpenpayAPI(
            System.getenv("ENVIRONMENT"),
            System.getenv("PRIVATE_KEY"),
             System.getenv("MERCHANT_ID"));
    }
}