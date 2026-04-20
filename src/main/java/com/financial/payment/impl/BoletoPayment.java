package com.financial.payment.impl;

import com.financial.model.Payment;
import com.financial.payment.PaymentStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BoletoPayment implements PaymentStrategy {

    private static final Logger log = LoggerFactory.getLogger(BoletoPayment.class);

    @Override
    public String key() {
        return "boleto";
    }

    @Override
    public void process(Payment payment) {
        log.info("Gerando boleto no valor de: {}", payment.getAmount());
    }
}
