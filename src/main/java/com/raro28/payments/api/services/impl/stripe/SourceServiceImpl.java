package com.raro28.payments.api.services.impl.stripe;

import com.raro28.payments.api.models.Charge;
import com.raro28.payments.api.services.SourceService;
import com.raro28.payments.api.services.exceptions.PaymentsException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class SourceServiceImpl implements SourceService {

    @Override
    public void remove(String id, String customerId) {
        try {
            com.stripe.model.PaymentMethod.retrieve(id).detach();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public String capture(String id, String customerId, Charge charge, String verifier) {
        try {
            PaymentIntent result = PaymentIntent.create(PaymentIntentCreateParams
                .builder()
                    .setAmount((long) charge.getAmount().getValue())
                    .setCurrency(charge.getAmount().getCurrency().name().toLowerCase())
                    .setCustomer(customerId)
                    .setDescription(charge.getDescription())
                    .setPaymentMethod(id)
                .build())
                .confirm();

            return result.getId();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }
    
}