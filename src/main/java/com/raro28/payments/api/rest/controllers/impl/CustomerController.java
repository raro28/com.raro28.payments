package com.raro28.payments.api.rest.controllers.impl;

import com.raro28.payments.api.rest.controllers.CustomerApi;
import com.raro28.payments.api.rest.exceptions.PaymentsException;
import com.raro28.payments.api.rest.models.CreatedResourceResponse;
import com.raro28.payments.api.rest.models.Customer;
import com.raro28.payments.api.rest.models.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import mx.openpay.client.Card;
import mx.openpay.client.core.OpenpayAPI;

@RestController
public class CustomerController implements CustomerApi {

    private final OpenpayAPI api;

    @Autowired
    public CustomerController(OpenpayAPI api){
        this.api = api;
    }

    @Override
    public ResponseEntity<CreatedResourceResponse> create(Customer customer) {
        try {
            mx.openpay.client.Customer result = api
            .customers()
            .create(new mx.openpay.client.Customer()
                .name(customer.getName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .requiresAccount(false));

            return ResponseEntity.ok(CreatedResourceResponse.builder().id(result.getId()).build());
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public ResponseEntity<CreatedResourceResponse> addSource(String id, Source source) {
        try {        
            Card result = api
                .cards()
                .create(id, new Card()
                    .cardNumber(source.getCardNumber())
                    .holderName(source.getHolderName())
                    .cvv2(source.getSecureCode())
                    .expirationMonth(source.getExpirationMonth())
                    .expirationYear(source.getExpirationYear()));

            return ResponseEntity.ok(CreatedResourceResponse.builder().id(result.getId()).build());
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public ResponseEntity<Void> remove(String id) {
        try {
            api.customers().delete(id);
        } catch (Exception e) {
            throw new PaymentsException(e);
        }

        return ResponseEntity.noContent().build();
    }

}