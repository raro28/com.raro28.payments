package com.raro28.payments.api.services.factories;

import com.raro28.payments.api.services.ChargeService;
import com.raro28.payments.api.services.CustomerService;
import com.raro28.payments.api.services.SourceService;

public interface ServiceFactory {
    ChargeService getChargeService();

    CustomerService getCustomerService();

    SourceService getSourceService();
}