package com.financial.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "Dados para processamento de um pagamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    @Schema(description = "Valor do pagamento", example = "150.00")
    @NotNull(message = "O valor do pagamento é obrigatório")
    @Positive(message = "O valor do pagamento deve ser positivo")
    private BigDecimal amount;

    @Schema(description = "Método de pagamento", example = "pix", allowableValues = {"pix", "boleto", "creditcard"})
    @NotBlank(message = "O método de pagamento é obrigatório")
    private String method;

    @Schema(description = "Identificador do cliente", example = "cliente-123")
    @NotBlank(message = "O ID do cliente é obrigatório")
    private String customerId;
}
