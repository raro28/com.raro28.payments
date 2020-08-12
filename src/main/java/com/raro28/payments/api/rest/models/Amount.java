package com.raro28.payments.api.rest.models;

import lombok.Data;

@Data
public class Amount {
    private Currency currency;
    private float value;
}