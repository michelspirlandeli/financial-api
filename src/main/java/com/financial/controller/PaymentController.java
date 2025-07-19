package com.financial.controller;

import com.financial.model.Payment;
import com.financial.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> pagar(@RequestBody Payment payment) {
        service.processPayment(payment);
        return ResponseEntity.ok("Pagamento realizado com sucesso.");
    }
}

