package com.raro28.payments.api.rest.controllers.impl;

import com.raro28.payments.api.models.Charge;
import com.raro28.payments.api.rest.controllers.SourceApi;
import com.raro28.payments.api.rest.models.CreatedResourceResponse;
import com.raro28.payments.api.services.SourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController implements SourceApi {

    private final SourceService service;

    @Autowired
    public SourceController(SourceService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> remove(String id, String customerId) {
        service.remove(id, customerId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CreatedResourceResponse> capture(String id, String customerId, Charge charge, String verifier) {
        return ResponseEntity.ok(CreatedResourceResponse.builder().id(service.capture(id, customerId, charge, verifier)).build());
    }
}