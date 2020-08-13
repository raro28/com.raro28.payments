package com.raro28.payments.api.services.impl.stripe;

import com.raro28.payments.api.models.Customer;
import com.raro28.payments.api.models.Source;
import com.raro28.payments.api.services.CustomerService;
import com.raro28.payments.api.services.exceptions.PaymentsException;
import com.stripe.model.PaymentMethod;
import com.stripe.model.SetupIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.param.PaymentMethodCreateParams.BillingDetails;
import com.stripe.param.PaymentMethodCreateParams.CardDetails;
import com.stripe.param.SetupIntentConfirmParams;
import com.stripe.param.SetupIntentCreateParams;
import com.stripe.param.SetupIntentCreateParams.Usage;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public String create(Customer customer) {
        try {
            com.stripe.model.Customer result = com.stripe.model.Customer.create(CustomerCreateParams
                .builder()
                    .setName(customer.getName() + (customer.getLastName() != null ? customer.getLastName() : ""))
                    .setEmail(customer.getEmail())
                .build());

            return result.getId();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public void remove(String id) {
        try {
            com.stripe.model.Customer.retrieve(id).delete();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

    @Override
    public String addSource(String id, Source source) {
        try {
            PaymentMethod  result = PaymentMethod.create(PaymentMethodCreateParams
                .builder()
                    .setType(PaymentMethodCreateParams.Type.CARD)
                    .setBillingDetails(BillingDetails
                        .builder()
                            .setName(source.getHolderName())
                        .build())
                    .setCard(CardDetails
                        .builder()
                            .setNumber(source.getCardNumber())
                            .setExpMonth((long) source.getExpirationMonth())
                            .setExpYear((long) source.getExpirationYear())
                            .setCvc(source.getSecureCode())
                     .build())
                .build());
            
            SetupIntent.create(SetupIntentCreateParams
                .builder()
                    .setCustomer(id)
                    .setUsage(Usage.OFF_SESSION)
                .build())
                .confirm(SetupIntentConfirmParams
                    .builder()
                        .setPaymentMethod(result.getId())
                    .build());

            return result.getId();
        } catch (Exception e) {
            throw new PaymentsException(e);
        }
    }

}