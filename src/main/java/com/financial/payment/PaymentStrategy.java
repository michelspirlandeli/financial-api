package com.financial.payment;

import com.financial.model.Payment;

public interface PaymentStrategy {
    void process(Payment payment);
}
