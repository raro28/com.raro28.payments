package com.raro28.payments.api.rest.controllers.impl;

import com.raro28.payments.api.rest.controllers.ChargeApi;
import com.raro28.payments.api.services.ChargeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargeController implements ChargeApi {
    private final ChargeService service;

    @Autowired
    public ChargeController(ChargeService service) {
        this.service = service;
    }
}