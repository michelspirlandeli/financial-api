package com.financial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Payment(BigDecimal amount, String method, String customerId) {
        this.amount = amount;
        this.method = method;
        this.customerId = customerId;
    }

    @NotNull(message = "O valor do pagamento é obrigatório")
    @Positive(message = "O valor do pagamento deve ser positivo")
    private BigDecimal amount;

    @NotBlank(message = "O método de pagamento é obrigatório")
    private String method;

    @NotBlank(message = "O ID do cliente é obrigatório")
    private String customerId;
}
