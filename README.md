# 💳 Projeto Spring Boot - Sistema de Pagamentos com SOLID

Este projeto é um exemplo prático de um sistema de pagamentos utilizando **Spring Boot**, com foco total na aplicação dos **princípios SOLID** na arquitetura e no código.

---

## ✅ Objetivo

Demonstrar como aplicar os **5 princípios SOLID** em um projeto realista, com um domínio simples: **meios de pagamento financeiros** como Cartão, Pix e Boleto.

---

## 🧱 Princípios SOLID Aplicados

### 1. 🧩 SRP — Single Responsibility Principle (Princípio da Responsabilidade Única)
> Cada classe deve ter uma única responsabilidade.

**Onde foi aplicado:**
- `Payment`: representa o modelo de dados.
- `PaymentRepository`: responsável pela persistência.
- `ReceiptGenerator`: responsável pela geração de recibos.
- `PaymentController`: responsável pela entrada da requisição HTTP.

Cada classe tem uma única razão para mudar, evitando acoplamento excessivo.

---

### 2. 🚪 OCP — Open/Closed Principle (Aberto para extensão, fechado para modificação)
> O código deve permitir extensão sem precisar ser modificado.

**Onde foi aplicado:**
- Criamos a interface `PaymentStrategy`.
- Cada método de pagamento (`PixPayment`, `CreditCardPayment`, `BoletoPayment`) implementa essa interface.
- `PaymentService` seleciona dinamicamente a estratégia com base no método de pagamento.

**Exemplo:**
Para adicionar `Paypal`, basta criar uma classe `PaypalPayment implements PaymentStrategy` sem modificar `PaymentService`.

---

### 3. 🔁 LSP — Liskov Substitution Principle (Princípio da Substituição de Liskov)
> Subclasses devem poder ser utilizadas como a superclasse sem afetar o funcionamento.

**Onde foi aplicado:**
- Todas as classes que implementam `PaymentStrategy` podem ser usadas de forma intercambiável no `PaymentService`.

Isso garante que o sistema funcione corretamente independentemente da implementação específica.

---

### 4. 🔍 ISP — Interface Segregation Principle (Princípio da Segregação de Interfaces)
> Interfaces devem ser pequenas e específicas para que classes não sejam forçadas a implementar métodos que não usam.

**Onde foi aplicado:**
- Criamos a interface `QRCodeSupport`, usada somente por métodos que suportam QR Code, como o Pix.

Isso evita que estratégias como Cartão ou Boleto implementem métodos que não utilizam.

---

### 5. 🔌 DIP — Dependency Inversion Principle (Princípio da Inversão de Dependência)
> Módulos de alto nível não devem depender de implementações, mas de abstrações.

**Onde foi aplicado:**
- `PaymentService` depende da interface `PaymentStrategy`, não das classes específicas.
- As estratégias são injetadas automaticamente pelo Spring via construtor.

Isso facilita testes unitários e promove um código desacoplado e extensível.

---

## ▶️ Como executar o projeto

1. **Pré-requisitos:**
    - Java 21
    - Maven

2. **Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/solid-spring-pagamento.git
cd solid-spring-pagamento
```

3. **Execute o projeto:**
```bash
./mvnw spring-boot:run
```

4. **Teste com Postman ou curl:**
```json
POST http://localhost:8080/pagamentos
Content-Type: application/json

{
  "amount": 150.0,
  "method": "pix",
  "customerId": "123"
}
```

---

## 📁 Estrutura do Projeto

```
src/main/java/com/exemplo/financeiro/
├── controller/
│   └── PaymentController.java
├── model/
│   └── Payment.java
├── payment/
│   ├── PaymentStrategy.java
│   ├── PixPayment.java
│   ├── CreditCardPayment.java
│   ├── BoletoPayment.java
│   └── QRCodeSupport.java
├── repository/
│   └── PaymentRepository.java
└── service/
    ├── PaymentService.java
    └── ReceiptGenerator.java
```

---

## 🧪 Testes
Você pode implementar testes com JUnit + Mockito focando em:
- `PaymentService` usando mocks para `PaymentStrategy`
- Validação de lógica de seleção de estratégia

---

## 💡 Possíveis Extensões
- Implementar persistência real com banco de dados
- Adicionar autenticação com Spring Security
- Criar testes automatizados
- Criar uma interface web com React ou Angular

---

## ✍️ Autor

Desenvolvido por Michel Spirlandeli — Inspirado por Clean Architecture, SOLID, e boas práticas de engenharia de software com Java.
