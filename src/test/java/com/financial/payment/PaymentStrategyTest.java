package com.financial.payment;

import com.financial.model.Payment;
import com.financial.payment.impl.BoletoPayment;
import com.financial.payment.impl.CreditCardPayment;
import com.financial.payment.impl.PixPayment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PaymentStrategyTest {

    private final Payment payment = new Payment(BigDecimal.valueOf(250), "pix", "cliente-1");

    @Test
    void pixDeveProcessarSemErros() {
        assertDoesNotThrow(() -> new PixPayment().process(payment));
    }

    @Test
    void boletoDeveProcessarSemErros() {
        assertDoesNotThrow(() -> new BoletoPayment().process(payment));
    }

    @Test
    void cartaoDeveProcessarSemErros() {
        assertDoesNotThrow(() -> new CreditCardPayment().process(payment));
    }
}
