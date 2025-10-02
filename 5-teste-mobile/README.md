# 📱 Teste Mobile - Calculadora do Google

Projeto de teste automatizado da calculadora do Google usando **Appium + SauceLabs + TestNG**.

## 🎯 Objetivos do Projeto

### 5.1 - Script Simples ✅

- Teste simples que realiza soma de dois números (5 + 3 = 8)

### 5.2 - Page Objects + CSV ✅

- Testes organizados em Page Objects
- Leitura de dados de arquivo CSV
- Pelo menos 3 cálculos diferentes

## 🏗️ Estrutura do Projeto

```
src/test/java/
├── CalculadoraTest.java         # Testes principais
├── pages/CalculadoraPage.java   # Page Objects
├── utils/CSVReader.java         # Leitor de CSV  
├── base/BaseTest.java          # Config SauceLabs
└── resources/calculos.csv      # Dados de teste
```

## 🚀 Tecnologias

- **Java 11**
- **Maven**
- **TestNG**
- **Appium**
- **SauceLabs** (Cloud Testing)
- **Page Object Pattern**

## 📊 Casos de Teste (CSV)

| Num1 | Operação | Num2 | Resultado |
| ---- | ---------- | ---- | --------- |
| 5    | +          | 3    | 8         |
| 10   | +          | 7    | 17        |
| 25   | +          | 15   | 40        |

## ▶️ Como Executar

```bash
# Executar todos os testes
mvn clean test

# Executar teste específico  
mvn test -Dtest=CalculadoraTest

# Executar apenas teste simples
mvn test -Dtest=CalculadoraTest#testeSomaSimples
```

## 🌐 SauceLabs

Testes executados no **SauceLabs Cloud**:

- **Dispositivo**: Samsung Galaxy S21 FE 5G GoogleAPI Emulator
- **Android**: 11.0
- **App**: Calculadora do Google

## ✅ Resultados

- ✅ 5.1 - Teste simples implementado
- ✅ 5.2 - Page Objects + CSV implementado
- ✅ Execução em cloud (SauceLabs)
- ✅ 4 testes executados (1 simples + 3 CSV)
