# Teste Mobile - Calculadora do Google

Este projeto implementa testes automatizados para a calculadora do Google utilizando Appium e TestNG.

## Estrutura do Projeto

```
src/
├── test/
│   ├── java/
│   │   ├── pages/
│   │   │   └── CalculadoraPage.java     # Page Object da calculadora
│   │   ├── utils/
│   │   │   └── CSVReader.java           # Utilitário para ler CSV
│   │   ├── base/
│   │   │   └── BaseTest.java            # Configuração base do Appium
│   │   └── CalculadoraTest.java         # Testes da calculadora
│   └── resources/
│       └── calculos.csv                 # Dados de teste
```

## Pré-requisitos

1. Java 11+
2. Maven
3. Android SDK
4. Appium Server
5. Emulador Android ou dispositivo físico
6. App da Calculadora do Google instalado

## Como executar

1. Iniciar o Appium Server:
```bash
appium
```

2. Iniciar o emulador Android ou conectar dispositivo

3. Executar os testes:
```bash
mvn test
```

## Funcionalidades

- ✅ Teste simples de soma (5 + 3 = 8)
- ✅ Testes parametrizados com dados do CSV
- ✅ Page Objects para organização do código
- ✅ Configuração base reutilizável

## Dados de Teste

O arquivo `calculos.csv` contém os seguintes casos de teste:
- 5 + 3 = 8
- 10 + 7 = 17
- 25 + 15 = 40
