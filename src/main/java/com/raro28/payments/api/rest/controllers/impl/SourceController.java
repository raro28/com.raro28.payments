package com.raro28.payments.api.rest.controllers.impl;

import java.math.BigDecimal;

import com.raro28.payments.api.rest.controllers.SourceApi;
import com.raro28.payments.api.rest.exceptions.PaymentsException;
import com.raro28.payments.api.rest.models.Charge;
import com.raro28.payments.api.rest.models.CreatedResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.core.requests.transactions.CreateCardChargeParams;

@RestController
public class SourceController implements SourceApi {

    private final OpenpayAPI api;

    @Autowired
    public SourceController(OpenpayAPI api) {
        this.api = api;
    }

    @Override
    public ResponseEntity<Void> remove(String id, String customerId) {
        try {
            api.cards().delete(customerId, id);
        } catch (Exception e) {
            throw new PaymentsException(e);
        }

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CreatedResourceResponse> capture(String id, String customerId, Charge charge, String verifier) {
        try {        
            mx.openpay.client.Charge result = api
                .charges()
                .createCharge(customerId, new CreateCardChargeParams()
                    .amount(BigDecimal.valueOf(charge.getAmount().getValue()))
                    .cardId(id)
                    .orderId(charge.getReference())
                    .description(charge.getDescription())
                    .deviceSessionId(verifier));

            return ResponseEntity.ok(CreatedResourceResponse.builder().id(result.getId()).build());
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }
}