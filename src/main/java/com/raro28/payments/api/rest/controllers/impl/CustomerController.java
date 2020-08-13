package com.raro28.payments.api.rest.controllers.impl;

import com.raro28.payments.api.models.Customer;
import com.raro28.payments.api.models.Source;
import com.raro28.payments.api.rest.controllers.CustomerApi;
import com.raro28.payments.api.rest.models.CreatedResourceResponse;
import com.raro28.payments.api.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerApi {

    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CreatedResourceResponse> create(Customer customer) {
        return ResponseEntity.ok(CreatedResourceResponse.builder().id(service.create(customer)).build());
    }

    @Override
    public ResponseEntity<CreatedResourceResponse> addSource(String id, Source source) {
        return ResponseEntity.ok(CreatedResourceResponse.builder().id(service.addSource(id, source)).build());
    }

    @Override
    public ResponseEntity<Void> remove(String id) {
        service.remove(id);

        return ResponseEntity.noContent().build();
    }

}