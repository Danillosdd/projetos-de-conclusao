# Caso de Teste Orientado a Dados - SauceDemo Login

## Informações Gerais

- **ID do Teste:** CT-001
- **Título:** Validação de login com diferentes tipos de usuários
- **Objetivo:** Verificar o comportamento do sistema de login com diferentes combinações de usuários e cenários
- **Site Testado:** https://www.saucedemo.com/
- **Data de Criação:** 01/10/2025

## Pré-condições

1. Navegador web instalado e funcional
2. Conexão com internet ativa
3. Acesso ao site SauceDemo disponível

## Dados de Teste

| ID | Username                | Password        | Resultado Esperado                                                                | Observações                         |
| -- | ----------------------- | --------------- | --------------------------------------------------------------------------------- | ------------------------------------- |
| 1  | standard_user           | secret_sauce    | Login bem-sucedido - Redirecionamento para página de produtos                    | Usuário padrão válido              |
| 2  | locked_out_user         | secret_sauce    | Erro: "Epic sadface: Sorry, this user has been locked out."                       | Usuário bloqueado                    |
| 3  | problem_user            | secret_sauce    | Login bem-sucedido - Possíveis problemas visuais na página                      | Usuário com problemas conhecidos     |
| 4  | performance_glitch_user | secret_sauce    | Login bem-sucedido - Possível lentidão no carregamento                          | Usuário com problemas de performance |
| 5  | error_user              | secret_sauce    | Login bem-sucedido - Possíveis erros funcionais                                  | Usuário com comportamentos de erro   |
| 6  | visual_user             | secret_sauce    | Login bem-sucedido - Possíveis problemas visuais                                 | Usuário com problemas visuais        |
| 7  | usuario_invalido        | secret_sauce    | Erro: "Epic sadface: Username and password do not match any user in this service" | Usuário inexistente                  |
| 8  | standard_user           | senha_incorreta | Erro: "Epic sadface: Username and password do not match any user in this service" | Senha incorreta                       |
| 9  | (vazio)                 | secret_sauce    | Erro: "Epic sadface: Username is required"                                        | Campo username vazio                  |
| 10 | standard_user           | (vazio)         | Erro: "Epic sadface: Password is required"                                        | Campo password vazio                  |
| 11 | (vazio)                 | (vazio)         | Erro: "Epic sadface: Username is required"                                        | Ambos campos vazios                   |

## Passos do Teste

### Para cada linha de dados:

1. **Acessar o site:** Navegar para https://www.saucedemo.com/
2. **Inserir credenciais:**
   - Preencher campo "Username" com o valor da coluna correspondente
   - Preencher campo "Password" com o valor da coluna correspondente
3. **Executar login:** Clicar no botão "LOGIN"
4. **Verificar resultado:** Confirmar se o comportamento observado corresponde ao resultado esperado
5. **Documentar:** Registrar qualquer discrepância ou comportamento adicional observado
6. **Limpar dados:** Fazer logout (se aplicável) ou recarregar a página para o próximo teste

## Critérios de Sucesso

- Todos os casos de teste devem apresentar o comportamento documentado na coluna "Resultado Esperado"
- Mensagens de erro devem ser claras e específicas
- Não deve haver quebras ou crashes no sistema
- O tempo de resposta deve ser razoável (< 5 segundos)

## Critérios de Falha

- Comportamento diferente do esperado
- Mensagens de erro inadequadas ou ausentes
- Crashes ou erros de sistema
- Tempo de resposta excessivo (> 10 segundos)

## Ambiente de Teste

- **Browsers:** Chrome, Firefox, Safari (testar em pelo menos 2)
- **Resolução:** 1920x1080 (desktop)
- **Sistema Operacional:** Windows 10/11, macOS, Linux

## Notas Adicionais

- Registrar screenshots dos erros encontrados
- Documentar diferenças de comportamento entre browsers
- Observar console do desenvolvedor para erros JavaScript
- Verificar responsividade em diferentes resoluções

---

## 👨‍💻 Autor

**Danillo Silva**

- GitHub: [@Danillosdd](https://github.com/Danillosdd)
- Projeto: [projetos-de-conclusao](https://github.com/Danillosdd/projetos-de-conclusao)

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software da Iterasys** e demonstra boas práticas em teste manual e orientado a dados com nomenclatura completamente em português brasileiro.

Todos os casos de teste validam sistematicamente diferentes cenários de login no SauceDemo através de uma abordagem data-driven estruturada.
