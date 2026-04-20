package com.financial.payment;

import com.financial.model.Payment;

public interface PaymentStrategy {
    String key();
    void process(Payment payment);
}
