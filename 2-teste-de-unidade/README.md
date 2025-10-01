# Projeto de Teste de Unidade

Este projeto demonstra a implementação de testes de unidade em Java utilizando JUnit 5, com foco em testes de métodos que calculam volumes de figuras geométricas.

## 🚀 Tecnologias Utilizadas

- **Java** - Linguagem de programação principal
- **JUnit 5** - Framework de teste para Java
- **Maven** - Gerenciador de dependências
- **VS Code** - Ambiente de desenvolvimento

## 📋 Funcionalidades Testadas

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

## 📁 Estrutura do Projeto

```
2-teste-de-unidade/
├── src/
│   ├── main/java/
│   │   └── com/iterasys/
│   │       └── Main.java
│   ├── test/java/
│   │   └── TesteUnidade.java
│   └── test/resources/
│       └── csv/
│           └── esfera.csv
├── pom.xml
└── README.md
```

## ⚙️ Como Executar

### Pré-requisitos

- Java 8 ou superior instalado
- Maven instalado
- VS Code com extensão Java

### Execução dos Testes

1. **Via linha de comando:**

```bash
mvn test
```

2. **Via VS Code:**
   - Abra o arquivo `TesteUnidade.java`
   - Clique no ícone "Run Test" ao lado de cada método de teste
   - Ou use `Ctrl+Shift+P` → "Java: Run Tests"

### Execução de Teste Específico

```bash
# Teste unitário simples do cubo
mvn test -Dtest=TesteUnidade#testeVolumeCubo

# Teste parametrizado do paralelepípedo
mvn test -Dtest=TesteUnidade#testeVolumePararelelepipedoDDT

# Teste com arquivo CSV da esfera
mvn test -Dtest=TesteUnidade#testeVolumeEsferaCSV
```

## 📊 Relatórios de Teste

Os resultados dos testes são exibidos no terminal e podem ser visualizados através do painel de testes do VS Code. Exemplo de saída:

```
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
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

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software** da **Iterasys**.
