# Critérios de Aceitação - SauceDemo E-commerce

## Feature: Processo de Compra Completo no SauceDemo

### História do Usuário

**Como um** usuário do SauceDemo
**Eu quero** realizar um processo de compra completo
**Para que** eu possa adquirir produtos de forma eficiente e segura

---

## Critério de Aceitação 1: Login e Acesso aos Produtos

**Dado que** eu sou um usuário válido
**Quando** eu realizo login no sistema
**Então** eu devo conseguir acessar o catálogo de produtos

### Exemplos:

| Tipo de Usuário       | Username                | Password     | Resultado Esperado                               |
| ---------------------- | ----------------------- | ------------ | ------------------------------------------------ |
| Usuário Padrão       | standard_user           | secret_sauce | Acesso liberado ao catálogo                     |
| Usuário com Problemas | problem_user            | secret_sauce | Acesso liberado, mas com possíveis bugs visuais |
| Usuário com Lentidão | performance_glitch_user | secret_sauce | Acesso liberado, mas com delays                  |
| Usuário Bloqueado     | locked_out_user         | secret_sauce | Acesso negado com mensagem explicativa           |

---

## Critério de Aceitação 2: Adição de Produtos ao Carrinho

**Dado que** estou logado e na página de produtos
**Quando** eu adiciono produtos ao carrinho
**Então** os produtos devem aparecer corretamente no carrinho com as informações adequadas

### Exemplos:

| Produto                  | Preço                                                 | Ação              | Resultado no Carrinho        |
| ------------------------ | ------------------------------------------------------ | ------------------- | ---------------------------- |
| Sauce Labs Backpack      | $29.99 | Adicionar | Produto listado com preço $29.99 |                     |                              |
| Sauce Labs Bike Light    | $9.99 | Adicionar | Produto listado com preço $9.99   |                     |                              |
| Sauce Labs Bolt T-Shirt  | $15.99                                                 | Adicionar e Remover | Produto removido do carrinho |
| Sauce Labs Fleece Jacket | $49.99 | Adicionar 2x | Quantidade = 2, Total = $99.98 |                     |                              |

---

## Critério de Aceitação 3: Processo de Checkout

**Dado que** tenho produtos no carrinho
**Quando** eu procedo para o checkout
**Então** devo conseguir finalizar a compra preenchendo as informações obrigatórias

### Exemplos de Informações de Checkout:

| First Name | Last Name | Postal Code | Resultado Esperado                     |
| ---------- | --------- | ----------- | -------------------------------------- |
| João      | Silva     | 01234-567   | Checkout concluído com sucesso        |
| Maria      | Santos    | 12345-678   | Checkout concluído com sucesso        |
| (vazio)    | Silva     | 01234-567   | Erro: "Error: First Name is required"  |
| João      | (vazio)   | 01234-567   | Erro: "Error: Last Name is required"   |
| João      | Silva     | (vazio)     | Erro: "Error: Postal Code is required" |

---

## Critério de Aceitação 4: Cálculos de Preços

**Dado que** tenho produtos selecionados
**Quando** visualizo o resumo da compra
**Então** os cálculos de subtotal, taxa e total devem estar corretos

### Exemplos de Cálculos:

| Produtos no Carrinho                        | Subtotal         | Tax     | Total Final |
| ------------------------------------------- | ---------------- | ------- | ----------- |
| 1x Backpack ($29.99) | $29.99               | $2.40 | $32.39   |         |             |
| 1x Bike Light ($9.99) + 1x T-Shirt ($15.99) | $25.98 | $2.08   | $28.06  |             |
| 2x Fleece Jacket ($49.99 cada) | $99.98     | $8.00 | $107.98  |         |             |
| 1 de cada produto (6 itens)                 | $129.94 | $10.40 | $140.34 |             |

---

## Critério de Aceitação 5: Navegação e Filtros

**Dado que** estou na página de produtos
**Quando** utilizo as opções de ordenação
**Então** os produtos devem ser exibidos na ordem selecionada

### Exemplos de Ordenação:

| Filtro Selecionado  | Primeiro Produto                  | Último Produto                   | Critério de Validação      |
| ------------------- | --------------------------------- | --------------------------------- | ----------------------------- |
| Name (A to Z)       | Sauce Labs Backpack               | Test.allTheThings() T-Shirt (Red) | Ordem alfabética crescente   |
| Name (Z to A)       | Test.allTheThings() T-Shirt (Red) | Sauce Labs Backpack               | Ordem alfabética decrescente |
| Price (low to high) | Sauce Labs Onesie                 | Sauce Labs Fleece Jacket          | Preços em ordem crescente    |
| Price (high to low) | Sauce Labs Fleece Jacket          | Sauce Labs Onesie                 | Preços em ordem decrescente  |

---

## Critério de Aceitação 6: Responsividade e Compatibilidade

**Dado que** acesso o site em diferentes dispositivos
**Quando** navego pelas funcionalidades
**Então** todas as funcionalidades devem estar acessíveis e funcionais

### Exemplos de Dispositivos/Browsers:

| Dispositivo | Resolução | Browser       | Funcionalidades Testadas  |
| ----------- | ----------- | ------------- | ------------------------- |
| Desktop     | 1920x1080   | Chrome        | Login, Carrinho, Checkout |
| Desktop     | 1920x1080   | Firefox       | Login, Carrinho, Checkout |
| Tablet      | 1024x768    | Safari        | Login, Carrinho, Checkout |
| Mobile      | 375x667     | Chrome Mobile | Login, Carrinho, Checkout |

---

## Definição de Pronto (Definition of Done)

✅ Todos os exemplos passam conforme especificado
✅ Não há erros de console JavaScript
✅ Tempo de resposta < 3 segundos para ações principais
✅ Funcionalidade testada em pelo menos 2 browsers diferentes
✅ Interface responsiva validada em desktop e mobile
✅ Mensagens de erro são claras e orientam o usuário
✅ Não há quebras visuais ou de layout
✅ Dados são persistidos corretamente durante a sessão

---

## 👨‍💻 Autor

**Danillo Silva**

- GitHub: [@Danillosdd](https://github.com/Danillosdd)
- Projeto: [projetos-de-conclusao](https://github.com/Danillosdd/projetos-de-conclusao)

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software da Iterasys** e demonstra boas práticas em critérios de aceitação e especificação por exemplos com nomenclatura completamente em português brasileiro.

Todos os critérios estabelecem expectativas claras e testáveis para o processo de compra completo no SauceDemo através de exemplos práticos e estruturados.
