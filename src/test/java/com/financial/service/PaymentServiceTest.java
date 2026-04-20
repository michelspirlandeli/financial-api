package com.financial.service;

import com.financial.exception.PaymentMethodNotFoundException;
import com.financial.model.Payment;
import com.financial.payment.PaymentStrategy;
import com.financial.payment.impl.BoletoPayment;
import com.financial.payment.impl.CreditCardPayment;
import com.financial.payment.impl.PixPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private PaymentService service;

    @BeforeEach
    void setUp() {
        List<PaymentStrategy> strategies = List.of(
                new PixPayment(),
                new BoletoPayment(),
                new CreditCardPayment()
        );
        service = new PaymentService(strategies);
    }

    @Test
    void deveProcesarPagamentoPix() {
        Payment payment = new Payment(BigDecimal.valueOf(100), "pix", "cliente-1");
        assertDoesNotThrow(() -> service.processPayment(payment));
    }

    @Test
    void deveProcesarPagamentoBoleto() {
        Payment payment = new Payment(BigDecimal.valueOf(200), "boleto", "cliente-2");
        assertDoesNotThrow(() -> service.processPayment(payment));
    }

    @Test
    void deveProcesarPagamentoCartao() {
        Payment payment = new Payment(BigDecimal.valueOf(300), "creditcard", "cliente-3");
        assertDoesNotThrow(() -> service.processPayment(payment));
    }

    @Test
    void deveLancarExcecaoParaMetodoDesconhecido() {
        Payment payment = new Payment(BigDecimal.valueOf(100), "bitcoin", "cliente-4");
        assertThrows(PaymentMethodNotFoundException.class, () -> service.processPayment(payment));
    }

    @Test
    void mensagemDeExcecaoDeveConterMetodo() {
        Payment payment = new Payment(BigDecimal.valueOf(100), "bitcoin", "cliente-4");
        PaymentMethodNotFoundException ex = assertThrows(
                PaymentMethodNotFoundException.class,
                () -> service.processPayment(payment)
        );
        assertTrue(ex.getMessage().contains("bitcoin"));
    }
}
