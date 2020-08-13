package com.raro28.payments.api.services.impl.openpay;

import java.math.BigDecimal;

import com.raro28.payments.api.models.Charge;
import com.raro28.payments.api.services.SourceService;
import com.raro28.payments.api.services.exceptions.PaymentsException;

import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.core.requests.transactions.CreateCardChargeParams;

public class SourceServiceImpl implements SourceService {
    private final OpenpayAPI api;

    public SourceServiceImpl(OpenpayAPI api){
        this.api = api;
    }

    @Override
    public void remove(String id, String customerId) {
        try {
            api.cards().delete(customerId, id);
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public String capture(String id, String customerId, Charge charge, String verifier) {
        try {        
            mx.openpay.client.Charge result = api
                .charges()
                .createCharge(customerId, new CreateCardChargeParams()
                    .amount(BigDecimal.valueOf(charge.getAmount().getValue()))
                    .cardId(id)
                    .orderId(charge.getReference())
                    .description(charge.getDescription())
                    .deviceSessionId(verifier));

            return result.getId();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }
    
}