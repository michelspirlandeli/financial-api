package com.financial.payment.impl;

import com.financial.model.Payment;
import com.financial.payment.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class BoletoPayment implements PaymentStrategy {

    @Override
    public void process(Payment payment) {
        System.out.println("Gerando boleto no valor de: " + payment.getAmount());
    }
}
