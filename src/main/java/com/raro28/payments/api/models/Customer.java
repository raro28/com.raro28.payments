package com.raro28.payments.api.models;

import lombok.Data;

@Data
public class Customer {
    private String name;
    private String lastName;
    private String email;
}