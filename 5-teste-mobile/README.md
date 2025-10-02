# 📱 Projeto de Automação de Testes Mobile - Calculadora do Google

Este projeto implementa testes mobile automatizados para a calculadora do Google usando **Appium + SauceLabs + TestNG** conforme solicitado no exercício 5 da formação em Teste de Software da Iterasys.

## 🎯 Objetivo

O projeto foi criado para demonstrar a automação de testes mobile utilizando a calculadora do Google no Android, implementando desde testes simples até arquiteturas organizadas com Page Objects e leitura de dados externos via CSV.

## 🛠️ Tecnologias Utilizadas

- **Java 11** - Linguagem de programação
- **Maven** - Gerenciamento de dependências e build
- **TestNG** - Framework de testes
- **Appium** - Automação de testes mobile
- **SauceLabs** - Plataforma de testes em nuvem
- **Page Object Pattern** - Padrão para organização dos testes

## 📁 Estrutura do Projeto

```
5-teste-mobile/
├── src/
│   ├── main/java/
│   │   └── com/saucelabs/
│   │       └── Main.java
│   └── test/
│       ├── java/
│       │   ├── CalculadoraTest.java         # Testes principais (5.1 e 5.2)
│       │   ├── base/
│       │   │   └── BaseTest.java            # Configuração SauceLabs
│       │   ├── pages/
│       │   │   └── CalculadoraPage.java     # Page Objects da calculadora
│       │   └── utils/
│       │       └── CSVReader.java           # Leitor de arquivo CSV
│       └── resources/
│           └── calculos.csv                 # Dados de teste
├── pom.xml
└── README.md
```

## 🧪 Implementações de Teste

### 5.1 - Script Simples (`testeSomaSimples`)

- **Objetivo**: Teste simples que realiza soma de dois números (5 + 3 = 8)
- **Características**:
  - ✅ Implementação direta com Appium
  - ✅ Teste fixo: 5 + 3 = 8
  - ✅ Validação com `assertEquals()`
  - ✅ Execução no SauceLabs

### 5.2 - Page Objects + CSV (`testeSomaComPageObjectsECSV`)

- **Objetivo**: Testes organizados em Page Objects com leitura de CSV
- **Características**:
  - ✅ Padrão Page Object para manutenibilidade
  - ✅ Leitura de dados do arquivo `calculos.csv`
  - ✅ Pelo menos 3 cálculos diferentes
  - ✅ `@DataProvider` do TestNG
  - ✅ Separação clara entre lógica de teste e interação com elementos

## 🎮 Cenários de Teste

### Teste Simples (5.1)

- **Operação**: 5 + 3 = 8
- **Validação**: Resultado deve ser "8"

### Testes com CSV (5.2)

- **Arquivo**: `src/test/resources/calculos.csv`
- **Dados**:

| Num1 | Operação | Num2 | Resultado |
| ---- | ---------- | ---- | --------- |
| 5    | +          | 3    | 8         |
| 10   | +          | 7    | 17        |
| 25   | +          | 15   | 40        |

## 🚀 Como Executar

### Pré-requisitos

- **Java 11** ou superior
- **Maven 3.6** ou superior
- **Conta SauceLabs** (credenciais configuradas)

### Configurar Credenciais SauceLabs

```bash
# Defina as variáveis de ambiente
export SAUCE_USERNAME=seu_usuario
export SAUCE_ACCESS_KEY=sua_chave
```

### Executar todos os testes

```bash
mvn clean test
```

### Executar teste específico

```bash
# 5.1 - Teste simples
mvn test -Dtest=CalculadoraTest#testeSomaSimples

# 5.2 - Testes com Page Objects e CSV
mvn test -Dtest=CalculadoraTest#testeSomaComPageObjectsECSV
```

### Executar com logs detalhados

```bash
mvn clean test -X
```

## 🌐 SauceLabs

### Configuração

- **Plataforma**: Android
- **Aplicativo**: Calculadora do Google
- **Device**: Emulador Android
- **Orientação**: Portrait

### Capabilities

```java
capabilities.setCapability("platformName", "Android");
capabilities.setCapability("appium:deviceName", "Google Pixel GoogleAPI Emulator");
capabilities.setCapability("appium:platformVersion", "8.1");
capabilities.setCapability("appium:automationName", "UiAutomator2");
capabilities.setCapability("appium:app", "storage:filename=Calculator_8.4 (503542421)_APKPure.apk");
```

## 📊 Resultados dos Testes

### Status Atual: 4 testes executados - 0 falhas - 100% de sucesso 🎉

| Implementação                    | Testes         | Tempo | Status                 |
| ---------------------------------- | -------------- | ----- | ---------------------- |
| **5.1** - Script Simples     | 1 ✅           | ~30s  | ✅ Funcionando         |
| **5.2** - Page Objects + CSV | 3 ✅           | ~90s  | ✅ Funcionando         |
| **Total**                    | **4** ✅ | ~120s | **100% Sucesso** |

### Exemplo de Saída

```
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 🎯 Conceitos Demonstrados

- **Automação Mobile** com Appium
- **Cloud Testing** com SauceLabs
- **Page Object Pattern** para manutenibilidade
- **Data-Driven Testing** com CSV e `@DataProvider`
- **TestNG** para organização e execução de testes
- **Configuração de Capabilities** para dispositivos Android
- **Estratégia de Seletores Múltiplos** para elementos dinâmicos
- **Nomenclatura em Português** - Projeto 100% em português brasileiro

## 📱 Aplicação Testada

- **Aplicativo**: Calculadora do Google
- **Versão**: 8.4 (503542421)
- **Plataforma**: Android 8.1
- **Funcionalidades**: Operações básicas de soma

### Elementos Testados

| Elemento  | ID                                            | Funcionalidade    |
| --------- | --------------------------------------------- | ----------------- |
| Dígitos  | `com.google.android.calculator:id/digit_X`  | Entrada numérica |
| Somar     | `com.google.android.calculator:id/op_add`   | Operação soma   |
| Igual     | `com.google.android.calculator:id/eq`       | Executar cálculo |
| Resultado | `com.google.android.calculator:id/result_*` | Exibir resultado  |

## 📋 Validações Realizadas

- ✅ **Operação de Soma** - Cálculos matemáticos corretos
- ✅ **Múltiplos Seletores** - Compatibilidade com diferentes versões
- ✅ **Leitura de CSV** - Dados externos carregados corretamente
- ✅ **Page Objects** - Separação clara de responsabilidades
- ✅ **Integração SauceLabs** - Execução em nuvem funcional

## 🔍 Detalhes Técnicos

### Imports Principais

```java
// TestNG
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

// Appium
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
```

### Anotações Utilizadas

- `@Test` - Testes TestNG
- `@DataProvider` - Fornecimento de dados para testes parametrizados
- `@AndroidFindBy` - Localização de elementos Android
- `@BeforeMethod` / `@AfterMethod` - Setup e cleanup

## 📝 Notas Importantes

- **SauceLabs**: Necessário configurar credenciais válidas
- **Seletores**: Implementação com múltiplos seletores para robustez
- **CSV**: Arquivo deve estar em `src/test/resources/`
- **Timeouts**: WebDriverWait configurado com 15 segundos
- **Português**: Toda nomenclatura e documentação em português brasileiro

## 🎓 Arquiteturas Implementadas

### 1. **Script Simples** (5.1)

- Teste direto com Appium
- Ideal para validações rápidas

### 2. **Page Objects + CSV** (5.2)

- Arquitetura escalável e maintível
- Testes orientados por dados externos

## 👨‍💻 Autor

**Danillo Silva**

- GitHub: [@Danillosdd](https://github.com/Danillosdd)
- Projeto: [projetos-de-conclusao](https://github.com/Danillosdd/projetos-de-conclusao)

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software da Iterasys** e demonstra boas práticas em automação de testes mobile com nomenclatura completamente em português brasileiro.

Todas as implementações validam com sucesso as operações de soma da calculadora do Google através de diferentes arquiteturas de teste.
