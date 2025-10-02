# Projetos de Conclusão - Formação em Teste de Software

Este repositório contém todos os projetos de conclusão desenvolvidos durante a **Formação em Teste de Software da Iterasys**, demonstrando competências em diferentes tipos de testes automatizados.

## 🎯 Objetivo

O repositório foi criado para consolidar e apresentar os conhecimentos adquiridos ao longo da formação, implementando projetos práticos que cobrem as principais áreas da automação de testes: testes manuais/exploratórios, testes de unidade, testes de API e testes web.

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação principal
- **Maven** - Gerenciamento de dependências e build
- **JUnit 5** - Framework de testes unitários
- **Rest-Assured** - Testes de API REST
- **Selenium WebDriver 4** - Automação de testes web
- **Cucumber** - Framework BDD (Behavior-Driven Development)
- **Chrome/ChromeDriver** - Browser para testes web
- **VS Code** - Ambiente de desenvolvimento

## 📁 Estrutura dos Projetos

```
projetos-de-conclusao/
├── 1-teste-manual-e-exploratorio/     # Exercício 1: Testes Manuais
│   └── [Documentação de testes manuais e exploratórios]
├── 2-teste-de-unidade/                # Exercício 2: Testes Unitários
│   ├── src/                           # Testes de cálculo de volumes
│   ├── pom.xml                        # Dependências JUnit 5
│   └── README.md                      # Documentação específica
├── 3-teste-de-api/                    # Exercício 3: Testes de API
│   ├── src/                           # Testes Restful Booker API
│   ├── pom.xml                        # Dependências Rest-Assured
│   └── README.md                      # Documentação específica
├── 4-teste-web/                       # Exercício 4: Testes Web
│   ├── src/                           # Testes SauceDemo (3 abordagens)
│   ├── pom.xml                        # Dependências Selenium/Cucumber
│   └── README.md                      # Documentação específica
└── README.md                          # Este arquivo
```

## 🧪 Projetos Implementados

### 1️⃣ Teste Manual e Exploratório

- **Aplicação**: SauceDemo
- **Foco**: Estratégias de teste manual e exploratório
- **Técnicas**: Casos de teste, cenários exploratórios, análise heurística
- **Status**: ✅ Concluído

### 2️⃣ Teste de Unidade - Calculadora de Volumes

- **Aplicação**: Biblioteca de cálculo de figuras geométricas
- **Foco**: Testes unitários com JUnit 5
- **Técnicas**:
  - Testes unitários simples
  - Testes parametrizados com `@CsvSource`
  - Testes orientados por dados com `@CsvFileSource`
- **Status**: ✅ Concluído (7 testes - 100% sucesso)

### 3️⃣ Teste de API - Restful Booker

- **Aplicação**: API de reservas de hotel
- **Foco**: Automação de testes de API REST
- **Técnicas**:
  - Autenticação com tokens
  - Operações CRUD completas
  - Testes parametrizados com massa de dados
  - Validação de status codes e responses
- **Status**: ✅ Concluído (18 testes - 100% sucesso)

### 4️⃣ Teste Web - SauceDemo

- **Aplicação**: E-commerce SauceDemo
- **Foco**: Automação web com múltiplas arquiteturas
- **Técnicas**:
  - **Script Simples**: JUnit + Selenium puro
  - **BDD Cucumber**: Cenários em português com Examples
  - **Page Objects**: Arquitetura escalável e maintível
- **Status**: ✅ Concluído (5 testes - 100% sucesso)

## 🚀 Como Executar Todos os Projetos

### Pré-requisitos Globais

- **Java 17** ou superior
- **Maven 3.9** ou superior
- **Google Chrome** (para testes web)
- **Git** para clone do repositório

### Clone do Repositório

```bash
git clone <url-do-seu-repositorio>
cd projetos-de-conclusao
```

### Execução por Projeto

```bash
# Testes de Unidade
cd 2-teste-de-unidade
mvn clean test

# Testes de API
cd ../3-teste-de-api
mvn clean test

# Testes Web
cd ../4-teste-web
mvn clean test
```

### Execução de Todos os Testes (Script)

```bash
#!/bin/bash
echo "🧪 Executando todos os projetos de teste..."

projects=("2-teste-de-unidade" "3-teste-de-api" "4-teste-web")

for project in "${projects[@]}"; do
    echo "📁 Executando: $project"
    cd "$project"
    mvn clean test
    cd ..
    echo "✅ $project concluído"
    echo ""
done

echo "🎉 Todos os testes executados!"
```

## 📊 Resultados Consolidados

| Projeto                      | Testes          | Sucesso        | Tempo          | Cobertura              |
| ---------------------------- | --------------- | -------------- | -------------- | ---------------------- |
| **2-teste-de-unidade** | 7 ✅            | 100%           | ~2s            | Cálculos matemáticos |
| **3-teste-de-api**     | 18 ✅           | 100%           | ~11s           | CRUD + Autenticação  |
| **4-teste-web**        | 5 ✅            | 100%           | ~26s           | Fluxo E-commerce       |
| **Total**              | **30** ✅ | **100%** | **~39s** | **Completa**     |

### Estatísticas Detalhadas

- ✅ **30 testes automatizados** executados com sucesso
- 🎯 **3 tipos diferentes** de automação implementados
- 🏗️ **6 arquiteturas diferentes** demonstradas
- 🌐 **100% em português brasileiro** - código e documentação
- 📚 **4 READMEs padronizados** com documentação completa

## 🎓 Conceitos e Técnicas Demonstradas

### Testes de Unidade

- ✅ Testes unitários com JUnit 5
- ✅ Testes parametrizados (`@CsvSource`, `@CsvFileSource`)
- ✅ Data-Driven Testing
- ✅ Assertions com tolerância para float
- ✅ Organização de dados de teste

### Testes de API

- ✅ Rest-Assured para automação de API
- ✅ Autenticação com tokens
- ✅ Serialização/Deserialização JSON
- ✅ Operações CRUD completas
- ✅ Validação de responses e status codes
- ✅ Testes parametrizados com CSV
- ✅ Hamcrest Matchers

### Testes Web

- ✅ Selenium WebDriver 4
- ✅ Page Object Pattern
- ✅ Behavior-Driven Development (BDD)
- ✅ Cucumber com Gherkin em português
- ✅ WebDriverManager
- ✅ Configurações anti-modal
- ✅ Multiple test architectures

### Boas Práticas Gerais

- ✅ Clean Code e nomenclatura clara
- ✅ Separação de responsabilidades
- ✅ Reutilização de código
- ✅ Documentação completa
- ✅ Versionamento com Git
- ✅ Padrões de projeto
- ✅ Testes estáveis e confiáveis

## 🏆 Destaques do Projeto

### Arquiteturas Implementadas

1. **Script Simples** - Abordagem direta para prototipação rápida
2. **Data-Driven Testing** - Testes orientados por dados externos
3. **BDD com Cucumber** - Cenários legíveis para stakeholders
4. **Page Object Pattern** - Arquitetura escalável para testes web
5. **API Testing** - Cobertura completa de serviços REST
6. **Unit Testing** - Validação de lógica de negócio

### Diferenciais Técnicos

- 🇧🇷 **100% Português Brasileiro** - Código, documentação e cenários
- 🎯 **Multiple Patterns** - Diferentes arquiteturas no mesmo domínio
- 📊 **Data-Driven** - Testes parametrizados em todos os níveis
- 🔧 **Production-Ready** - Configurações robustas e estáveis
- 📚 **Documentação Completa** - READMEs detalhados para cada projeto
- ⚡ **Fast Execution** - Otimizações para execução rápida

## 🌐 Aplicações Testadas

| Aplicação              | Tipo            | URL                                  | Domínio    |
| ------------------------ | --------------- | ------------------------------------ | ----------- |
| **Calculadora**    | Biblioteca Java | Local                                | Matemática |
| **Restful Booker** | API REST        | https://restful-booker.herokuapp.com | Reservas    |
| **SauceDemo**      | Web App         | https://www.saucedemo.com            | E-commerce  |

## 📋 Roadmap e Melhorias Futuras

- [ ] **Testes de Performance** - JMeter ou Gatling
- [ ] **CI/CD Pipeline** - GitHub Actions para execução automática
- [ ] **Relatórios Avançados** - Allure Reports
- [ ] **Testes de Acessibilidade** - axe-core integration
- [ ] **Docker** - Containerização dos testes
- [ ] **Parallel Execution** - Execução paralela de testes

## 🎯 Aprendizados e Competências Desenvolvidas

### Técnicas

- Estratégias de automação em múltiplas camadas
- Padrões de arquitetura para testes maintíveis
- Integração de ferramentas de teste modernas
- Técnicas de debugging e troubleshooting

### Ferramentas

- Domínio completo do ecossistema Java de testes
- Selenium WebDriver avançado
- Rest-Assured para APIs
- Cucumber para BDD
- Maven para build management

### Soft Skills

- Documentação técnica profissional
- Organização de projetos complexos
- Nomenclatura consistente e clara
- Pensamento analítico aplicado a testes

## 👨‍💻 Autor

**Danillo Silva**

- **GitHub**: [@Danillosdd](https://github.com/Danillosdd)
- **LinkedIn**: [@danillosdd](https://www.linkedin.com/in/danillosdd)
- **Formação**: Teste de Software - Iterasys (2024)

## 🏫 Sobre a Formação

**Iterasys - Formação em Teste de Software**

Esta formação abrangente cobriu:

- Fundamentos de Teste de Software
- Estratégias de Teste Manual e Exploratório
- Automação de Testes em múltiplas camadas
- Ferramentas modernas do mercado
- Boas práticas e padrões da indústria
- Projetos práticos com aplicações reais

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software da Iterasys** e demonstra a aplicação prática de conhecimentos em automação de testes com foco em qualidade, manutenibilidade e nomenclatura completamente em português brasileiro.

**Todos os projetos validam com sucesso suas respectivas funcionalidades e demonstram diferentes abordagens e técnicas de automação de testes.**

---

### 🎉 **Status Geral**: Formação Concluída com Excelência!

**30 testes automatizados • 100% de sucesso • 4 projetos entregues • Documentação completa**

*Última atualização: Outubro 2025*
