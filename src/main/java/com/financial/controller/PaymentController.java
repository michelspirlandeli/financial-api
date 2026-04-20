package com.financial.controller;

import com.financial.dto.PaymentRequestDTO;
import com.financial.model.Payment;
import com.financial.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pagamentos", description = "Endpoint para processamento de pagamentos")
@RestController
@RequestMapping("/pagamentos")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @Operation(summary = "Processar pagamento", description = "Processa um pagamento usando o método informado (pix, boleto, creditcard)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "422", description = "Método de pagamento não suportado")
    })
    @PostMapping
    public ResponseEntity<String> pagar(@Valid @RequestBody PaymentRequestDTO dto) {
        Payment payment = new Payment(dto.getAmount(), dto.getMethod(), dto.getCustomerId());
        service.processPayment(payment);
        return ResponseEntity.ok("Pagamento realizado com sucesso.");
    }
}

