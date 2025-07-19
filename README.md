# 💳 Projeto Spring Boot - Sistema de Pagamentos com SOLID

Este projeto é um exemplo prático de um sistema de pagamentos utilizando **Spring Boot** com foco total na aplicação dos **princípios SOLID**.

---

## ✅ Objetivo

Demonstrar como aplicar os **5 princípios SOLID** em um projeto realista, com um domínio simples: **meios de pagamento financeiros** como Cartão, Pix e Boleto.

---

## 🧱 Princípios SOLID Aplicados

### 1. 🧩 SRP — Single Responsibility Principle (Princípio da Responsabilidade Única)

> Cada classe deve ter uma única responsabilidade.

No projeto:

- `Payment`: representa o modelo de pagamento.
- `PaymentRepository`: responsável apenas por salvar dados.
- `ReceiptGenerator`: responsável apenas por gerar recibos.

Isso evita que uma classe tenha múltiplas razões para mudar.

---

### 2. 🚪 OCP — Open/Closed Principle (Aberto para extensão, fechado para modificação)

> É possível estender o comportamento sem modificar o código existente.

No projeto:

- Criamos a interface `PaymentStrategy`.
- Cada meio de pagamento (Pix, Cartão, Boleto) implementa essa interface.
- Podemos adicionar novos tipos de pagamento sem modificar o serviço principal.

Exemplo:
```java
public interface PaymentStrategy {
    void process(Payment payment);
}
