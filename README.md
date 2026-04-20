# 💳 Projeto Spring Boot - Sistema de Pagamentos com SOLID

Este projeto é um exemplo prático de um sistema de pagamentos utilizando **Spring Boot**, com foco na aplicação dos **princípios SOLID** e em boas práticas de desenvolvimento para produção.

---

## ✅ Objetivo

Demonstrar como aplicar os **5 princípios SOLID** em um projeto realista, com um domínio simples: **meios de pagamento financeiros** como Cartão, Pix e Boleto.

---

## 🧱 Princípios SOLID Aplicados

### 1. 🧩 SRP — Single Responsibility Principle
> Cada classe deve ter uma única responsabilidade.

- `PaymentRequestDTO`: representa o contrato de entrada da API.
- `Payment`: entidade de domínio persistida no banco.
- `PaymentRepository`: responsável pela persistência via JPA.
- `ReceiptGenerator`: responsável pela geração de recibos.
- `PaymentController`: responsável pela entrada da requisição HTTP.

---

### 2. 🚪 OCP — Open/Closed Principle
> O código deve permitir extensão sem precisar ser modificado.

- A interface `PaymentStrategy` define o contrato de processamento.
- Cada método (`PixPayment`, `CreditCardPayment`, `BoletoPayment`) implementa essa interface.
- Para adicionar `PaypalPayment`, basta criar uma nova classe — sem tocar em `PaymentService`.

---

### 3. 🔁 LSP — Liskov Substitution Principle
> Subclasses devem poder ser utilizadas no lugar da superclasse sem afetar o funcionamento.

- Todas as implementações de `PaymentStrategy` são intercambiáveis no `PaymentService`.

---

### 4. 🔍 ISP — Interface Segregation Principle
> Interfaces devem ser pequenas e específicas.

- A interface `QRCodeSupport` é usada somente por métodos que suportam QR Code.
- A interface `PaymentStrategy` expõe apenas `key()` e `process()`, sem métodos desnecessários.

---

### 5. 🔌 DIP — Dependency Inversion Principle
> Módulos de alto nível não devem depender de implementações, mas de abstrações.

- `PaymentService` depende da interface `PaymentStrategy`, não das classes concretas.
- As estratégias são injetadas automaticamente pelo Spring via construtor.

---

## 🚀 Stack e Boas Práticas

| Recurso | Detalhe |
|---|---|
| **Java 21 + Spring Boot 3.5** | Base do projeto |
| **Validação** | `spring-boot-starter-validation` com `@Valid`, `@NotNull`, `@Positive` |
| **Persistência** | Spring Data JPA + banco H2 em memória |
| **DTOs** | `PaymentRequestDTO` separado da entidade `Payment` |
| **Tratamento de erros** | `GlobalExceptionHandler` com `@RestControllerAdvice` |
| **Documentação** | Springdoc OpenAPI (Swagger UI) |
| **Logging** | SLF4J + Logback (sem `System.out.println`) |
| **Testes** | JUnit 5 + Mockito + MockMvc (14 testes) |

---

## ▶️ Como executar

**Pré-requisitos:** Java 21, Maven

```bash
git clone https://github.com/michelspirlandeli/financial-api.git
cd financial-api
./mvnw spring-boot:run
```

**Exemplo de requisição:**
```bash
curl -X POST http://localhost:8080/pagamentos \
  -H "Content-Type: application/json" \
  -d '{"amount": 150.0, "method": "pix", "customerId": "cliente-123"}'
```

**Métodos suportados:** `pix`, `boleto`, `creditcard`

---

## 📖 Documentação da API

Com a aplicação rodando, acesse:

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

---

## 📁 Estrutura do Projeto

```
src/main/java/com/financial
├── controller/
│   └── PaymentController.java
├── dto/
│   └── PaymentRequestDTO.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── InvalidPaymentException.java
│   └── PaymentMethodNotFoundException.java
├── model/
│   └── Payment.java
├── payment/
│   ├── PaymentStrategy.java
│   ├── QRCodeSupport.java
│   └── impl/
│       ├── BoletoPayment.java
│       ├── CreditCardPayment.java
│       └── PixPayment.java
├── repository/
│   └── PaymentRepository.java
└── service/
    ├── PaymentService.java
    └── ReceiptGenerator.java
```

---

## 🧪 Testes

```bash
./mvnw test
```

| Classe | Cobertura |
|---|---|
| `PaymentServiceTest` | Processamento dos 3 métodos, método desconhecido, mensagem de exceção |
| `PaymentControllerTest` | 200 válido, 400 por campos inválidos, 422 por método não suportado |
| `PaymentStrategyTest` | Execução das 3 strategies sem erros |

---

## ✍️ Autor

Desenvolvido por Michel Spirlandeli — Inspirado por Clean Architecture, SOLID e boas práticas de engenharia de software com Java.
