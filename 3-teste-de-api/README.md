# Projeto de Automação de Testes da API - Restful Booker

Este repositório contém a automação dos testes da API do Restful Booker, desenvolvido como parte da formação em Teste de Software da Iterasys.

## 🎯 Objetivo

O projeto foi criado para demonstrar a automação de testes de API utilizando a API Restful Booker (https://restful-booker.herokuapp.com/), que simula um sistema de reservas de hotel, implementando autenticação e operações CRUD completas.

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Maven** - Gerenciamento de dependências e build
- **JUnit 5** - Framework de testes
- **Rest-Assured** - Biblioteca para testes de API REST
- **Gson** - Serialização/deserialização JSON
- **Hamcrest** - Matchers para assertions

## 📁 Estrutura do Projeto

```
src/
├── main/java/io/swagger/Main.java
└── test/
    ├── java/
    │   ├── AuthRequest.java         # Modelo para requisição de autenticação
    │   ├── AuthResponse.java        # Modelo para resposta de autenticação
    │   ├── Booking.java             # Modelo principal de reserva
    │   ├── BookingDates.java        # Modelo para datas da reserva
    │   ├── BookingResponse.java     # Modelo para resposta de criação de reserva
    │   ├── TestAuth.java            # Testes de autenticação
    │   ├── TestBooking.java         # Testes de operações de reserva
    │   └── TestSuite.java           # Suite para executar todos os testes
    └── resources/
        ├── csv/
        │   ├── authMassa.csv        # Dados para testes parametrizados de auth
        │   └── bookingMassa.csv     # Dados para testes parametrizados de booking
        └── json/
            ├── auth1.json           # Dados de exemplo para autenticação
            └── booking1.json        # Dados de exemplo para reserva
```

## 🧪 Cenários de Teste

### Autenticação (`TestAuth.java`)
- ✅ Autenticar com credenciais válidas
- ❌ Falhar com credenciais inválidas
- ❌ Falhar com dados vazios
- 📊 Testes parametrizados com massa de dados

### Reservas (`TestBooking.java`)
- ✅ Criar nova reserva
- 📋 Consultar todas as reservas
- 🔍 Consultar reserva por ID
- ✏️ Atualizar reserva existente
- 🗑️ Deletar reserva
- 🔍 Filtrar reservas por nome
- 📅 Filtrar reservas por data
- ❌ Acessar reserva inexistente
- 📊 Testes parametrizados com massa de dados

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven 3.6 ou superior

### Executar todos os testes
```bash
mvn test
```

### Executar classe específica
```bash
mvn test -Dtest=TestAuth
mvn test -Dtest=TestBooking
```

### Executar teste específico
```bash
mvn test -Dtest=TestAuth#testAuthValidCredentials
```

## 📊 Dados de Teste

### Credenciais Padrão (auth1.json)
```json
{
    "username": "admin",
    "password": "password123"
}
```

### Exemplo de Reserva (booking1.json)
```json
{
    "firstname": "João",
    "lastname": "Silva",
    "totalprice": 150,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2025-10-01",
        "checkout": "2025-10-05"
    },
    "additionalneeds": "Breakfast"
}
```

## 🌐 API Testada

- **Base URL**: https://restful-booker.herokuapp.com
- **Documentação**: https://restful-booker.herokuapp.com/apidoc/index.html

### Endpoints Testados

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth` | Autenticação |
| GET | `/booking` | Listar reservas |
| POST | `/booking` | Criar reserva |
| GET | `/booking/{id}` | Consultar reserva |
| PUT | `/booking/{id}` | Atualizar reserva |
| DELETE | `/booking/{id}` | Deletar reserva |

## 📊 Relatórios

Os relatórios de teste são gerados automaticamente em:
- `target/surefire-reports/` - Relatórios detalhados em XML/TXT
- Console com logs das requisições e respostas

## 🎓 Conceitos Demonstrados

- **Data-Driven Testing**: Testes parametrizados com CSV
- **Test Data Management**: Arquivos JSON e CSV organizados
- **API Authentication**: Gerenciamento de tokens
- **CRUD Operations**: Operações completas de Create, Read, Update, Delete
- **Response Validation**: Validação completa de respostas
- **Error Handling**: Testes de cenários de erro
- **Clean Code**: Código organizado e reutilizável

## ✅ Resultados dos Testes

**Status atual: 36 testes executados - 0 falhas - 100% de sucesso** 🎉

- TestAuth: 6 testes ✅
- TestBooking: 12 testes ✅  
- TestSuite: Executa ambas as classes ✅

## 🤝 Contribuição

Este projeto faz parte da formação em Teste de Software da Iterasys e demonstra boas práticas em automação de testes de API.
