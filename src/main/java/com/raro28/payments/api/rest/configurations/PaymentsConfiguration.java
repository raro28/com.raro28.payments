package com.raro28.payments.api.rest.configurations;

import com.raro28.payments.api.services.ChargeService;
import com.raro28.payments.api.services.CustomerService;
import com.raro28.payments.api.services.SourceService;
import com.raro28.payments.api.services.impl.openpay.ChargeServiceImpl;
import com.raro28.payments.api.services.impl.openpay.CustomerServiceImpl;
import com.raro28.payments.api.services.impl.openpay.SourceServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.openpay.client.core.OpenpayAPI;

@Configuration
public class PaymentsConfiguration {

    @Autowired
    private OpenpayAPI api;

    @Bean
    public ChargeService chargeService() {
        return new ChargeServiceImpl(api);
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl(api);
    }

    @Bean
    public SourceService sourceService() {
        return new SourceServiceImpl(api);
    }
}