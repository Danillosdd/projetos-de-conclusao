# Projeto de Automação de Testes Web - SauceDemo

Este projeto implementa testes web automatizados para o site SauceDemo (https://www.saucedemo.com/) conforme solicitado no exercício 4 da formação em Teste de Software da Iterasys.

## 🎯 Objetivo

O projeto foi criado para demonstrar três abordagens diferentes de automação de testes web, validando a consistência de informações de produtos (nome e preço) desde a listagem até o carrinho de compras, implementando desde scripts simples até arquiteturas mais avançadas com Page Objects.

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Maven** - Gerenciamento de dependências e build
- **JUnit 5** - Framework de testes
- **Selenium WebDriver 4.25.0** - Automação web
- **Cucumber 7.18.0** - Framework BDD (Behavior-Driven Development)
- **WebDriverManager** - Gerenciamento automático de drivers
- **Chrome/ChromeDriver** - Browser utilizado nos testes

## 📁 Estrutura do Projeto

```
4-teste-web/
├── src/
│   ├── main/java/
│   │   └── com/blazemeter/Main.java
│   └── test/
│       ├── java/
│       │   ├── SimpleTest.java                    # 4.1 - Script Simples
│       │   ├── steps/
│       │   │   ├── ComprarProdutosSteps.java      # 4.2 - Cucumber Steps
│       │   │   └── TestRunner.java                # 4.2 - Cucumber Runner
│       │   ├── stepsPO/
│       │   │   ├── ComprarProdutosPOSteps.java    # 4.3 - Page Object Steps
│       │   │   └── TestRunnerPO.java              # 4.3 - Page Object Runner
│       │   └── pages/
│       │       ├── BasePage.java                  # Classe base para páginas
│       │       ├── LoginPage.java                 # Página de login
│       │       ├── InventoryPage.java             # Página de produtos
│       │       ├── ProductDetailsPage.java        # Página de detalhes
│       │       └── CartPage.java                  # Página do carrinho
│       └── resources/
│           ├── features/
│           │   └── ComprarProdutos.feature        # Cenários Cucumber
│           └── featuresPO/
│               └── ComprarProdutosPO.feature      # Cenários Page Objects
├── pom.xml
├── README.md
└── DOCUMENTACAO_ARQUIVOS.md
```

## 🧪 Implementações de Teste

### 4.1 - Script Simples (`SimpleTest.java`)

- **Objetivo**: Teste JUnit puro que valida produto do início ao carrinho
- **Características**:
  - ✅ Implementação direta com Selenium WebDriver
  - ✅ Validação completa do fluxo de compra
  - ✅ Configurações anti-modal para estabilidade
  - ✅ Assertions com `assertEquals()`

### 4.2 - Cucumber com Examples

**Arquivos**:
- `ComprarProdutos.feature` - Cenários em Gherkin português
- `ComprarProdutosSteps.java` - Implementação dos steps
- `TestRunner.java` - Runner de execução

**Características**:
- ✅ BDD (Behavior-Driven Development)
- ✅ `Esquema do Cenário` com tabela de `Exemplos`
- ✅ Testes múltiplos produtos: Sauce Labs Backpack ($29.99) e Sauce Labs Bike Light ($9.99)
- ✅ Steps em português: `@Dado`, `@Quando`, `@Então`

### 4.3 - Page Objects

**Arquivos**:
- `ComprarProdutosPO.feature` - Cenários BDD para Page Objects
- `ComprarProdutosPOSteps.java` - Steps que utilizam Page Objects
- `TestRunnerPO.java` - Runner específico
- **Pages**: `BasePage`, `LoginPage`, `InventoryPage`, `ProductDetailsPage`, `CartPage`

**Características**:
- ✅ Padrão Page Object para manutenibilidade
- ✅ Separação clara entre lógica de teste e interação com elementos
- ✅ Herança de `BasePage` para funcionalidades comuns
- ✅ Encapsulamento de elementos e ações por página

## 🎮 Cenários de Teste

### Fluxo Principal Testado (Comum às 3 implementações)

1. **Login** - Acesso com usuário `standard_user` / `secret_sauce`
2. **Listagem** - Captura nome e preço do produto na página principal
3. **Detalhes** - Navega para detalhes e valida consistência
4. **Carrinho** - Adiciona produto e valida informações finais
5. **Validação** - Verifica se nome e preço se mantêm idênticos em todas as etapas

### Produtos Testados

| Produto | Preço | Implementação |
|---------|-------|---------------|
| Sauce Labs Backpack | $29.99 | Todas as 3 |
| Sauce Labs Bike Light | $9.99 | Cucumber (4.2 e 4.3) |

## 🚀 Como Executar

### Pré-requisitos

- **Java 17** ou superior
- **Maven 3.6** ou superior
- **Google Chrome** instalado

### Executar todos os testes

```bash
mvn clean test
```

### Executar implementação específica

```bash
# 4.1 - Script Simples
mvn test -Dtest=SimpleTest

# 4.2 - Cucumber com Examples
mvn test -Dtest=TestRunner

# 4.3 - Page Objects
mvn test -Dtest=TestRunnerPO
```

### Executar com logs detalhados

```bash
mvn clean test -X
```

## 🔧 Configurações Especiais

### Prevenção de Modal "Mude sua senha"

Todas as implementações utilizam configurações preventivas do Chrome:

```java
// Flags específicas para desabilitar modal de senha
options.addArguments("--disable-save-password-bubble");
options.addArguments("--disable-features=PasswordManager,AutofillServerCommunication");

// Preferências para desabilitar serviços de senha
Map<String, Object> prefs = new HashMap<>();
prefs.put("credentials_enable_service", false);
prefs.put("profile.password_manager_enabled", false);
options.setExperimentalOption("prefs", prefs);
```

### Configurações de Estabilidade

- Perfil temporário (`--user-data-dir=/tmp/chrome-test-profile`)
- Modo incógnito (`--incognito`)
- Window maximizada
- WebDriverWait com timeout de 15 segundos
- Implicit wait de 5 segundos

## 📊 Resultados dos Testes

### Status Atual: 5 testes executados - 0 falhas - 100% de sucesso 🎉

| Implementação | Testes | Tempo | Status |
|---------------|--------|-------|--------|
| **4.1** - SimpleTest | 1 ✅ | ~3s | ✅ Funcionando |
| **4.2** - Cucumber Examples | 2 ✅ | ~6s | ✅ Funcionando |
| **4.3** - Page Objects | 2 ✅ | ~17s | ✅ Funcionando |
| **Total** | **5** ✅ | ~26s | **100% Sucesso** |

### Exemplo de Saída

```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 🎯 Conceitos Demonstrados

- **Automação Web** com Selenium WebDriver 4
- **Behavior-Driven Development (BDD)** com Cucumber
- **Page Object Pattern** para manutenibilidade
- **Data-Driven Testing** com Examples em Gherkin
- **Configuração de Browser** para estabilidade de testes
- **Assertions e Validações** consistentes
- **Organização de Projeto** em múltiplas arquiteturas
- **Nomenclatura em Português** - Projeto 100% em português brasileiro

## 🌐 Aplicação Testada

- **URL**: https://www.saucedemo.com/
- **Tipo**: E-commerce simulado (SauceDemo)
- **Credenciais**: `standard_user` / `secret_sauce`
- **Funcionalidades**: Login, Listagem de Produtos, Detalhes, Carrinho

### Páginas Testadas

| Página | URL | Elementos Validados |
|--------|-----|-------------------|
| Login | `/` | Formulário de login |
| Inventory | `/inventory.html` | Lista de produtos, nomes, preços |
| Product Details | `/inventory-item.html?id=X` | Detalhes do produto |
| Cart | `/cart.html` | Itens no carrinho |

## 📋 Validações Realizadas

- ✅ **Login bem-sucedido** - Redirecionamento para página de produtos
- ✅ **Consistência de Nome** - Mesmo nome em listagem, detalhes e carrinho
- ✅ **Consistência de Preço** - Mesmo preço em todas as etapas
- ✅ **Navegação Correta** - Fluxo completo sem erros
- ✅ **Elementos Presentes** - Todos os elementos necessários disponíveis

## 🔍 Detalhes Técnicos

### Imports Principais

```java
// Selenium
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

// JUnit 5
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

// Cucumber
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
```

### Anotações Utilizadas

- `@Test` - Testes JUnit 5
- `@BeforeEach` / `@AfterEach` - Setup e cleanup
- `@Dado` / `@Quando` / `@Então` - Steps Cucumber em português
- `@CucumberOptions` - Configuração do runner

## 📝 Notas Importantes

- **Modal Prevenção**: Configurações específicas eliminam modal "Mude sua senha"
- **Estabilidade**: Múltiplas configurações para execução consistente
- **Manutenibilidade**: Page Objects facilitam manutenção futura
- **Cobertura**: Três abordagens diferentes para o mesmo cenário
- **Português**: Toda nomenclatura e documentação em português brasileiro

## 🎓 Arquiteturas Implementadas

### 1. **Script Simples** (4.1)
- Abordagem direta com JUnit + Selenium
- Ideal para testes rápidos e prototipação

### 2. **BDD com Cucumber** (4.2)  
- Cenários legíveis por não-técnicos
- Examples para testes orientados por dados

### 3. **Page Objects** (4.3)
- Arquitetura escalável e mantível
- Separação clara de responsabilidades

## 👨‍💻 Autor

**Danillo Silva**

- GitHub: [@Danillosdd](https://github.com/Danillosdd)
- Projeto: [projetos-de-conclusao](https://github.com/Danillosdd/projetos-de-conclusao)

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software da Iterasys** e demonstra boas práticas em automação de testes web com nomenclatura completamente em português brasileiro.

Todas as implementações validam com sucesso a consistência dos dados do produto (nome e preço) através das diferentes telas do fluxo de compra.