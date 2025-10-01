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
    │   ├── SolicitacaoAutenticacao.java   # Modelo para requisição de autenticação
    │   ├── RespostaAutenticacao.java      # Modelo para resposta de autenticação
    │   ├── Reserva.java                   # Modelo principal de reserva
    │   ├── DatasReserva.java              # Modelo para datas da reserva
    │   ├── RespostaReserva.java           # Modelo para resposta de criação de reserva
    │   ├── TesteAutenticacao.java         # Testes de autenticação
    │   ├── TesteReserva.java              # Testes de operações de reserva
    │   └── SuiteDeTestes.java             # Suite para executar todos os testes
    └── resources/
        ├── csv/
        │   ├── massaDadosAutenticacao.csv # Dados para testes parametrizados de autenticação
        │   └── massaDadosReserva.csv      # Dados para testes parametrizados de reserva
        └── json/
            ├── autenticacao1.json         # Dados de exemplo para autenticação
            └── reserva1.json              # Dados de exemplo para reserva
```

## 🧪 Cenários de Teste

### Autenticação (`TesteAutenticacao.java`)

- ✅ Autenticar com credenciais válidas
- ❌ Falhar com credenciais inválidas
- ❌ Falhar com dados vazios
- 📊 Testes parametrizados com massa de dados

### Reservas (`TesteReserva.java`)

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
mvn test -Dtest=TesteAutenticacao
mvn test -Dtest=TesteReserva
```

### Executar teste específico

```bash
mvn test -Dtest=TesteAutenticacao#testarAutenticacaoComCredenciaisValidas
```

## 📊 Dados de Teste

### Credenciais Padrão (autenticacao1.json)

```json
{
    "username": "admin",
    "password": "password123"
}
```

### Exemplo de Reserva (reserva1.json)

```json
{
    "firstname": "Maria",
    "lastname": "Santos",
    "totalprice": 150,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2024-01-15",
        "checkout": "2024-01-20"
    },
    "additionalneeds": "Café da manhã"
}
```

### Massa de Dados - Autenticação (massaDadosAutenticacao.csv)

```csv
nomeUsuario,senha
admin,password123
usuario1,senhaErrada
testUser,123456
```

### Massa de Dados - Reserva (massaDadosReserva.csv)

```csv
primeiroNome,ultimoNome,precoTotal,depositoPago,dataCheckin,dataCheckout,necessidadesAdicionais
Ana,Silva,200,true,2024-03-01,2024-03-05,Quarto silencioso
Carlos,Oliveira,180,false,2024-03-10,2024-03-12,Berço para bebê
Fernanda,Costa,300,true,2024-04-01,2024-04-07,Vista para o mar
Roberto,Pereira,120,true,2024-04-15,2024-04-17,Café da manhã
```

## 🌐 API Testada

- **Base URL**: https://restful-booker.herokuapp.com
- **Documentação**: https://restful-booker.herokuapp.com/apidoc/index.html

### Endpoints Testados

| Método | Endpoint          | Descrição       |
| ------- | ----------------- | ----------------- |
| POST    | `/auth`         | Autenticação    |
| GET     | `/booking`      | Listar reservas   |
| POST    | `/booking`      | Criar reserva     |
| GET     | `/booking/{id}` | Consultar reserva |
| PUT     | `/booking/{id}` | Atualizar reserva |
| DELETE  | `/booking/{id}` | Deletar reserva   |

## 🔧 Configuração

A configuração da API está no método `@BeforeAll` de cada classe de teste:

- **Base URI**: https://restful-booker.herokuapp.com
- **Content-Type**: application/json
- **Logging**: Habilitado para falhas de validação

## 📊 Relatórios

Os relatórios de teste são gerados automaticamente em:

- `target/surefire-reports/` - Relatórios do Maven Surefire
- Console com logs detalhados das requisições e respostas

### 📝 Notas Importantes

- **Token de Autenticação**: Obtido automaticamente nos testes que necessitam
- **Massa de Dados**: Utiliza arquivos CSV para testes parametrizados
- **Validações**: Múltiplas validações por teste (status, body, headers)
- **Cleanup**: Alguns testes criam dados temporários removidos automaticamente

## 🎓 Conceitos Demonstrados

- **Data-Driven Testing**: Testes parametrizados com CSV
- **Test Data Management**: Arquivos JSON e CSV organizados
- **API Authentication**: Gerenciamento de tokens
- **CRUD Operations**: Operações completas de Create, Read, Update, Delete
- **Response Validation**: Validação completa de respostas
- **Error Handling**: Testes de cenários de erro
- **Clean Code**: Código organizado e reutilizável
- **Nomenclatura em Português**: Projeto 100% em português brasileiro

## ✅ Resultados dos Testes

**Status atual: 18 testes executados - 0 falhas - 100% de sucesso** 🎉

- TesteAutenticacao: 6 testes ✅
- TesteReserva: 12 testes ✅
- SuiteDeTestes: Executa ambas as classes ✅

## 🤝 Contribuição

Este projeto faz parte da formação em Teste de Software da Iterasys e demonstra boas práticas em automação de testes de API com nomenclatura completamente em português brasileiro.
