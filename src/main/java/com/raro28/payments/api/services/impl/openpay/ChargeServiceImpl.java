package com.raro28.payments.api.services.impl.openpay;

import com.raro28.payments.api.services.ChargeService;

import mx.openpay.client.core.OpenpayAPI;

public class ChargeServiceImpl implements ChargeService{
    private final OpenpayAPI api;

    public ChargeServiceImpl(OpenpayAPI api){
        this.api = api;
    }
}