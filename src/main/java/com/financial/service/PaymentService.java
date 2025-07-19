package com.financial.service;

import com.financial.model.Payment;
import com.financial.payment.PaymentStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final Map<String, PaymentStrategy> strategyMap;

    public PaymentService(List<PaymentStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(s -> s.getClass().getSimpleName().toLowerCase(), s -> s));
    }

    public void processPayment(Payment payment) {
        String key = payment.getMethod().toLowerCase() + "payment"; // ex: "pixpayment"
        PaymentStrategy strategy = strategyMap.get(key);

        if (strategy == null) {
            throw new RuntimeException("Método de pagamento não encontrado.");
        }

        strategy.process(payment);
    }
}
