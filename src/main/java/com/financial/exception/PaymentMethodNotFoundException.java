package com.financial.exception;

public class PaymentMethodNotFoundException extends RuntimeException {

    public PaymentMethodNotFoundException(String method) {
        super("Método de pagamento não suportado: " + method);
    }
}