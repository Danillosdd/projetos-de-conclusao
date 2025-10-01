# language: pt
Funcionalidade: Comprar produtos no SauceDemo
  Como um usuário do SauceDemo
  Quero consultar produtos e adicioná-los ao carrinho
  Para validar que as informações se mantêm consistentes

  Esquema do Cenário: Validar produto do início ao carrinho
    Dado que acesso o site SauceDemo
    E faço login com usuário "standard_user" e senha "secret_sauce"
    Quando seleciono o produto "<produto>"
    Então valido que o nome "<produto>" e preço "<preco>" estão corretos na listagem
    E vou para a página de detalhes do produto
    E valido que o nome "<produto>" e preço "<preco>" estão corretos nos detalhes
    E adiciono o produto ao carrinho
    E vou para o carrinho
    Então valido que o nome "<produto>" e preço "<preco>" estão corretos no carrinho

    Exemplos:
      | produto                | preco  |
      | Sauce Labs Backpack    | $29.99 |
      | Sauce Labs Bike Light  | $9.99  |