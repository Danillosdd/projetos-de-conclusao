#Executar no Terminal:
#mvn clean test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.features="src/test/resources/featuresPO"
#mvn clean test -Dtest=Runner
#mvn clean test -Dtest=stepsPO.Runner
# language: pt
Funcionalidade: Compra de produtos no SauceDemo

  Como usuário do SauceDemo
  Quero realizar a compra de produtos
  Para garantir que o fluxo de compra está funcionando corretamente

  Esquema do Cenário: Fluxo completo de compra de produtos
    Dado que estou na página de login do SauceDemo
    Quando faço login com usuário "<usuario>" e senha "<senha>"
    E adiciono o produto "<produto>" ao carrinho
    E acesso o carrinho
    E prossigo para o checkout
    E preencho os dados de compra com nome "<nome>", sobrenome "<sobrenome>" e CEP "<cep>"
    E finalizo a compra
    Então devo ver a mensagem de confirmação "<mensagem>"

    Exemplos:
      | usuario      | senha        | produto                  | nome    | sobrenome | cep     | mensagem                  |
      | standard_user| secret_sauce | Sauce Labs Backpack      | João    | Silva     | 12345   | Thank you for your order! |
      | standard_user| secret_sauce | Sauce Labs Bike Light    | Maria   | Souza     | 54321   | Thank you for your order! |
      | standard_user| secret_sauce | Sauce Labs Bolt T-Shirt  | Pedro   | Lima      | 67890   | Thank you for your order! |
      | standard_user| secret_sauce | Sauce Labs Fleece Jacket | Ana     | Costa     | 98765   | Thank you for your order! |