package com.raro28.payments.api.services.factories.impl.stripe;

import com.raro28.payments.api.services.ChargeService;
import com.raro28.payments.api.services.CustomerService;
import com.raro28.payments.api.services.SourceService;
import com.raro28.payments.api.services.factories.ServiceFactory;
import com.raro28.payments.api.services.impl.stripe.ChargeServiceImpl;
import com.raro28.payments.api.services.impl.stripe.CustomerServiceImpl;
import com.raro28.payments.api.services.impl.stripe.SourceServiceImpl;
import com.stripe.Stripe;

public class ServiceFactoryImpl implements ServiceFactory {

    static {
        Stripe.apiKey = System.getenv("PRIVATE_KEY");
    }

    @Override
    public ChargeService getChargeService() {
        return new ChargeServiceImpl();
    }

    @Override
    public CustomerService getCustomerService() {
        return new CustomerServiceImpl();
    }

    @Override
    public SourceService getSourceService() {
        return new SourceServiceImpl();
    }

}