package com.financial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financial.exception.PaymentMethodNotFoundException;
import com.financial.model.Payment;
import com.financial.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    @Test
    void deveRetornar200ParaPagamentoValido() throws Exception {
        Payment payment = new Payment(BigDecimal.valueOf(150), "pix", "cliente-1");

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isOk());

        verify(paymentService).processPayment(any());
    }

    @Test
    void deveRetornar400QuandoAmountNulo() throws Exception {
        Payment payment = new Payment(null, "pix", "cliente-1");

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void deveRetornar400QuandoAmountNegativo() throws Exception {
        Payment payment = new Payment(BigDecimal.valueOf(-10), "pix", "cliente-1");

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void deveRetornar400QuandoMethodVazio() throws Exception {
        Payment payment = new Payment(BigDecimal.valueOf(100), "", "cliente-1");

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void deveRetornar422ParaMetodoNaoSuportado() throws Exception {
        Payment payment = new Payment(BigDecimal.valueOf(100), "bitcoin", "cliente-1");
        doThrow(new PaymentMethodNotFoundException("bitcoin")).when(paymentService).processPayment(any());

        mockMvc.perform(post("/pagamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").exists());
    }
}
