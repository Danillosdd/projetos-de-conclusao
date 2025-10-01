# Projeto de Automação de Testes de Unidade - Calculadora de Volumes

Este projeto implementa testes de unidade automatizados para métodos de cálculo de volumes de figuras geométricas conforme solicitado no exercício 2 da formação em Teste de Software da Iterasys.

## 🎯 Objetivo

O projeto foi criado para demonstrar diferentes abordagens de testes de unidade em Java, validando cálculos de volumes de figuras geométricas (cubo, paralelepípedo e esfera), implementando desde testes unitários simples até testes parametrizados com dados externos.

## �️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Maven** - Gerenciamento de dependências e build
- **JUnit 5** - Framework de testes
- **VS Code** - Ambiente de desenvolvimento

## � Estrutura do Projeto

```
2-teste-de-unidade/
├── src/
│   ├── main/java/
│   │   └── com/iterasys/
│   │       └── Main.java                    # Classe com métodos de cálculo de volume
│   ├── test/java/
│   │   └── TesteUnidade.java                # Classe com todos os testes unitários
│   └── test/resources/
│       └── csv/
│           └── esfera.csv                   # Dados para testes parametrizados
├── pom.xml
└── README.md
```

## �📋 Funcionalidades Testadas

O projeto testa os seguintes métodos de cálculo de volume da classe `Main`:

- **Volume do Cubo** - `volumeCubo(float lado)` - Calcula o volume de um cubo dado o tamanho do lado
- **Volume do Paralelepípedo** - `volumePararelelepipedo(float comprimento, float largura, float altura)` - Calcula o volume de um paralelepípedo dados comprimento, largura e altura
- **Volume da Esfera** - `volumeEsfera(float raio)` - Calcula o volume de uma esfera dado o raio

## 🧪 Tipos de Teste Implementados

### 1. Teste Unitário Simples (`testeVolumeCubo`)

- Testa o método `volumeCubo()` com valor fixo (lado = 4)
- Verifica se o resultado é 64 (4³)
- Utiliza `@Test` e `assertEquals()` básico

### 2. Teste Parametrizado com @CsvSource (`testeVolumePararelelepipedoDDT`)

- Testa o método `volumePararelelepipedo()` com múltiplas combinações de dados
- Casos de teste:
  - `2, 3, 4 → 24.0`
  - `5, 2, 3 → 30.0`
  - `1, 1, 1 → 1.0`
  - `6, 4, 2 → 48.0`
  - `3.5, 2, 4 → 28.0`
- Dados fornecidos diretamente na anotação `@CsvSource`

### 3. Teste Parametrizado com @CsvFileSource (`testeVolumeEsferaCSV`)

- Testa o método `volumeEsfera()` utilizando dados do arquivo `/csv/esfera.csv`
- Inclui tolerância de 0.001f para comparação de números de ponto flutuante
- Primeira linha do CSV é ignorada (`numLinesToSkip = 1`)
- Inclui mensagem personalizada de erro com o valor do raio

## 🚀 Como Executar

### Pré-requisitos

- **Java 17** ou superior
- **Maven 3.6** ou superior
- **VS Code** com extensão Java

### Executar todos os testes

```bash
mvn clean test
```

### Executar via VS Code

- Abra o arquivo `TesteUnidade.java`
- Clique no ícone "Run Test" ao lado de cada método de teste
- Ou use `Ctrl+Shift+P` → "Java: Run Tests"

### Executar teste específico

```bash
# Teste unitário simples do cubo
mvn test -Dtest=TesteUnidade#testeVolumeCubo

# Teste parametrizado do paralelepípedo  
mvn test -Dtest=TesteUnidade#testeVolumePararelelepipedoDDT

# Teste com arquivo CSV da esfera
mvn test -Dtest=TesteUnidade#testeVolumeEsferaCSV
```

### Executar com logs detalhados

```bash
mvn clean test -X
```

## 📊 Resultados dos Testes

### Status Atual: 7 testes executados - 0 falhas - 100% de sucesso 🎉

| Tipo de Teste | Método | Casos | Status |
|---------------|--------|-------|--------|
| **Unitário Simples** | testeVolumeCubo | 1 ✅ | ✅ Funcionando |
| **Parametrizado @CsvSource** | testeVolumePararelelepipedoDDT | 5 ✅ | ✅ Funcionando |
| **Parametrizado @CsvFileSource** | testeVolumeEsferaCSV | 1 ✅ | ✅ Funcionando |
| **Total** | **3 métodos** | **7** ✅ | **100% Sucesso** |

### Exemplo de Saída

```
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 📝 Dados de Teste

### Arquivo CSV (esfera.csv)

O arquivo `src/test/resources/csv/esfera.csv` contém dados de teste para o cálculo do volume da esfera:

- **Formato**: `raio, volume_esperado`
- **Primeira linha**: Cabeçalho (ignorada nos testes)
- **Delimitador**: Vírgula (`,`)

Exemplo:

```csv
raio,volume_esperado
1.0,4.188
2.0,33.510
3.0,113.097
```

## 🎯 Conceitos Demonstrados

- **Testes Unitários** com JUnit 5 usando `@Test`
- **Testes Parametrizados** usando `@ParameterizedTest`
- **Data-Driven Testing** com:
  - `@CsvSource` - dados inline no código
  - `@CsvFileSource` - dados externos em arquivo CSV
- **Assertions** com tolerância para números de ponto flutuante (`assertEquals` com delta)
- **Organização de dados de teste** em arquivos de recursos
- **Mensagens personalizadas de erro** para melhor debugging
- **Configuração de delimitadores** e pulo de linhas em arquivos CSV
- **Clean Code** - Código organizado e reutilizável
- **Nomenclatura em Português** - Projeto 100% em português brasileiro

## 🔍 Detalhes Técnicos

### Imports Utilizados

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
```

### Anotações JUnit 5

- `@Test` - Marca um método como teste unitário
- `@ParameterizedTest` - Permite execução do teste com múltiplos parâmetros
- `@CsvSource` - Fornece dados de teste diretamente no código
- `@CsvFileSource` - Carrega dados de teste de arquivo CSV externo

## 👨‍💻 Autor

**Danillo Silva**

- GitHub: [@Danillosdd](https://github.com/Danillosdd)
- Projeto: [projetos-de-conclusao](https://github.com/Danillosdd/projetos-de-conclusao)

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software da Iterasys** e demonstra boas práticas em testes unitários com nomenclatura completamente em português brasileiro.

Todas as implementações validam com sucesso os cálculos de volumes das figuras geométricas através de diferentes abordagens de teste.
