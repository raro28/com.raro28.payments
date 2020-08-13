package com.raro28.payments.api.services.exceptions;

public class PaymentsException extends RuntimeException{

    private static final long serialVersionUID = -6783932315669346233L;

    public PaymentsException(Throwable e) {
        super(e);
    }
}