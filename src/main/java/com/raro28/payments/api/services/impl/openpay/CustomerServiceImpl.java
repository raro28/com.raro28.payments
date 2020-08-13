package com.raro28.payments.api.services.impl.openpay;

import com.raro28.payments.api.models.Customer;
import com.raro28.payments.api.models.Source;
import com.raro28.payments.api.services.CustomerService;
import com.raro28.payments.api.services.exceptions.PaymentsException;

import mx.openpay.client.Card;
import mx.openpay.client.core.OpenpayAPI;

public class CustomerServiceImpl implements CustomerService {

    private final OpenpayAPI api;

    public CustomerServiceImpl(OpenpayAPI api){
        this.api = api;
    }

    @Override
    public String create(Customer customer) {
        try {
            mx.openpay.client.Customer result = api
            .customers()
            .create(new mx.openpay.client.Customer()
                .name(customer.getName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .requiresAccount(false));

            return result.getId();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public void remove(String id) {
        try {
            api.customers().delete(id);
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public String addSource(String id, Source source) {
        try {        
            Card result = api
                .cards()
                .create(id, new Card()
                    .cardNumber(source.getCardNumber())
                    .holderName(source.getHolderName())
                    .cvv2(source.getSecureCode())
                    .expirationMonth(source.getExpirationMonth())
                    .expirationYear(source.getExpirationYear()));

            return result.getId();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }
    
}