package com.raro28.payments.api.models;

import lombok.Data;

@Data
public class Source {
    private String cardNumber;
    private String holderName;
    private int expirationMonth;
    private int expirationYear;
    private String secureCode;
}
