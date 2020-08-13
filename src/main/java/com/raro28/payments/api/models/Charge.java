package com.raro28.payments.api.models;

import lombok.Data;

@Data
public class Charge {
    private String reference;
    private String description;
    private Amount amount;
}