package com.raro28.payments.api.services;

import com.raro28.payments.api.models.Customer;
import com.raro28.payments.api.models.Source;

public interface CustomerService {
    String create(Customer customer);

    void remove(String id);

    String addSource(String id, Source source);
}