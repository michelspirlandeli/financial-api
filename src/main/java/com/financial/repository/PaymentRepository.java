package com.financial.repository;

import com.financial.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
    public void save(Payment payment) {
        // simulação de persistência
        System.out.println("Salvando pagamento: " + payment);
    }
}
