package com.financial.payment.impl;

import com.financial.model.Payment;
import com.financial.payment.PaymentStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(CreditCardPayment.class);

    @Override
    public String key() {
        return "creditcard";
    }

    @Override
    public void process(Payment payment) {
        log.info("Processando pagamento com cartão: {}", payment.getAmount());
    }
}
