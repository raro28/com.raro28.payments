package com.raro28.payments.api.services;

import com.raro28.payments.api.models.Charge;

public interface SourceService {
    void remove(String id, String customerId);

    String capture(String id, String customerId, Charge charge, String verifier);
}