package com.raro28.payments.api.rest.configurations;

import com.raro28.payments.api.services.ChargeService;
import com.raro28.payments.api.services.CustomerService;
import com.raro28.payments.api.services.SourceService;
import com.raro28.payments.api.services.factories.ServiceFactory;
import com.raro28.payments.api.services.factories.impl.stripe.ServiceFactoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.openpay.client.core.OpenpayAPI;

@Configuration
public class PaymentsConfiguration {

    @Autowired
    private OpenpayAPI api;

    @Bean
    public ServiceFactory serviceFactory(){
        return new ServiceFactoryImpl();
    }

    @Bean
    public ChargeService chargeService(ServiceFactory serviceFactory) {
        return serviceFactory.getChargeService();
    }

    @Bean
    public CustomerService customerService(ServiceFactory serviceFactory) {
        return serviceFactory.getCustomerService();
    }

    @Bean
    public SourceService sourceService(ServiceFactory serviceFactory) {
        return serviceFactory.getSourceService();
    }
}