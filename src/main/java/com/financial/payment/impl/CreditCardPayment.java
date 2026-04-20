package com.financial.payment.impl;

import com.financial.model.Payment;
import com.financial.payment.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentStrategy {

    @Override
    public String key() {
        return "creditcard";
    }

    @Override
    public void process(Payment payment) {
        System.out.println("Processando pagamento com cartão: " + payment.getAmount());
    }
}
