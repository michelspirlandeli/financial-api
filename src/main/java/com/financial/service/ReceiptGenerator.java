package com.financial.service;

import com.financial.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class ReceiptGenerator {
    public String generate(Payment payment) {
        return "Recibo: Pagamento de R$" + payment.getAmount();
    }
}
