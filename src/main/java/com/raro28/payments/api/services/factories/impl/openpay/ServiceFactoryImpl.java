package com.raro28.payments.api.services.factories.impl.openpay;

import com.raro28.payments.api.services.ChargeService;
import com.raro28.payments.api.services.CustomerService;
import com.raro28.payments.api.services.SourceService;
import com.raro28.payments.api.services.factories.ServiceFactory;
import com.raro28.payments.api.services.impl.openpay.ChargeServiceImpl;
import com.raro28.payments.api.services.impl.openpay.CustomerServiceImpl;
import com.raro28.payments.api.services.impl.openpay.SourceServiceImpl;

import mx.openpay.client.core.OpenpayAPI;

public class ServiceFactoryImpl implements ServiceFactory {
    private final OpenpayAPI api;

    public ServiceFactoryImpl(OpenpayAPI api){
        this.api = api;
    }

    @Override
    public ChargeService getChargeService() {
        return new ChargeServiceImpl(api);
    }

    @Override
    public CustomerService getCustomerService() {
        return new CustomerServiceImpl(api);
    }

    @Override
    public SourceService getSourceService() {
        return new SourceServiceImpl(api);
    }
    
}