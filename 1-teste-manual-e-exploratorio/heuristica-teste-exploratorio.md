# Heurística para Teste Exploratório - SauceDemo

## 📋 Guia de Teste Exploratório para E-commerce SauceDemo

### 🎯 Objetivo

Este guia fornece pontos-chave para observação durante testes exploratórios no site SauceDemo, ajudando a identificar possíveis problemas, inconsistências e oportunidades de melhoria.

---

## 🔐 **ÁREA: AUTENTICAÇÃO E SEGURANÇA**

### Pontos-Chave a Observar:

#### ✅ **Login e Logout**

- [ ] **Credenciais válidas:** Teste com todos os usuários fornecidos
- [ ] **Credenciais inválidas:** Observe mensagens de erro específicas
- [ ] **Campos obrigatórios:** Teste envio de formulário com campos vazios
- [ ] **Sesão:** Verifique se logout realmente encerra a sessão
- [ ] **Redirecionamentos:** Confirme se direcionam para páginas corretas
- [ ] **Tempo de resposta:** Login deve ser rápido (<3 segundos)

#### 🔍 **Segurança**

- [ ] **URL após login:** Verifique se contém tokens ou informações sensíveis
- [ ] **Histórico do browser:** Teste botão "voltar" após logout
- [ ] **Múltiplas abas:** Teste comportamento com login simultâneo
- [ ] **Injeção de código:** Teste caracteres especiais nos campos

---

## 🛍️ **ÁREA: CATÁLOGO DE PRODUTOS**

### Pontos-Chave a Observar:

#### ✅ **Exibição de Produtos**

- [ ] **Carregamento:** Todos os produtos aparecem corretamente
- [ ] **Imagens:** Verificar se todas as imagens carregam
- [ ] **Preços:** Conferir formatação e consistência de valores
- [ ] **Descrições:** Textos completos e sem caracteres estranhos
- [ ] **Layout:** Alinhamento e organização visual

#### 🔄 **Filtros e Ordenação**

- [ ] **Dropdown de ordenação:** Teste todas as opções disponíveis
- [ ] **Ordem alfabética:** Confirme se A-Z e Z-A funcionam corretamente
- [ ] **Ordem de preço:** Verifique crescente e decrescente
- [ ] **Persistência:** Filtro mantido ao navegar entre páginas

#### 🎨 **Aspectos Visuais**

- [ ] **Responsividade:** Teste em diferentes tamanhos de tela
- [ ] **Quebras de layout:** Observe elementos sobrepostos ou desalinhados
- [ ] **Fontes e cores:** Consistência visual em todo o site
- [ ] **Botões:** Hover, click, estados ativos/inativos

---

## 🛒 **ÁREA: CARRINHO DE COMPRAS**

### Pontos-Chave a Observar:

#### ✅ **Funcionalidades do Carrinho**

- [ ] **Adicionar produtos:** Botão muda para "REMOVE" após adicionar
- [ ] **Contador do carrinho:** Badge atualiza com número correto
- [ ] **Produtos únicos:** Não deve duplicar o mesmo produto
- [ ] **Remoção:** Função "REMOVE" funciona corretamente
- [ ] **Carrinho vazio:** Comportamento quando não há produtos

#### 🧮 **Cálculos**

- [ ] **Subtotal:** Soma correta dos preços individuais
- [ ] **Taxa (Tax):** Cálculo percentual aplicado corretamente
- [ ] **Total final:** Subtotal + Taxa = Total correto
- [ ] **Formatação:** Valores monetários com formato apropriado

---

## 📝 **ÁREA: CHECKOUT E FINALIZAÇÃO**

### Pontos-Chave a Observar:

#### ✅ **Formulário de Informações**

- [ ] **Campos obrigatórios:** Validação de First Name, Last Name, Postal Code
- [ ] **Mensagens de erro:** Específicas para cada campo vazio
- [ ] **Formatação:** Aceita diferentes formatos de CEP/Postal Code
- [ ] **Navegação:** Botões "CANCEL" e "CONTINUE" funcionais

#### 📋 **Resumo da Compra**

- [ ] **Informações do produto:** Nome, descrição, preço corretos
- [ ] **Informações de pagamento:** Dados fictícios apropriados
- [ ] **Informações de entrega:** Endereço baseado no formulário
- [ ] **Botão finalizar:** "FINISH" completa o processo

#### ✨ **Página de Confirmação**

- [ ] **Mensagem de sucesso:** Confirmação clara da compra
- [ ] **Botão "BACK HOME":** Retorna à página de produtos
- [ ] **Carrinho:** Deve estar vazio após finalização

---

## 🌐 **ÁREA: NAVEGAÇÃO E USABILIDADE**

### Pontos-Chave a Observar:

#### ✅ **Menu Hambúrguer**

- [ ] **Abertura/fechamento:** Funciona suavemente
- [ ] **Opções disponíveis:** All Items, About, Logout, Reset App State
- [ ] **Links funcionais:** Cada opção direciona corretamente
- [ ] **Reset App State:** Limpa carrinho e reseta estado

#### 🧭 **Navegação Geral**

- [ ] **Breadcrumbs:** Links de retorno funcionais
- [ ] **Botão voltar do browser:** Não quebra a aplicação
- [ ] **URLs amigáveis:** Endereços fazem sentido
- [ ] **Ações consecutivas:** Múltiplos cliques não causam problemas

---

## ⚡ **ÁREA: PERFORMANCE E COMPATIBILIDADE**

### Pontos-Chave a Observar:

#### ✅ **Velocidade**

- [ ] **Carregamento inicial:** Site abre rapidamente
- [ ] **Transições:** Mudanças de página são fluidas
- [ ] **Ações do usuário:** Resposta imediata aos cliques
- [ ] **Imagens:** Carregam sem atrasos perceptíveis

#### 🖥️ **Compatibilidade**

- [ ] **Browsers diferentes:** Chrome, Firefox, Safari, Edge
- [ ] **Dispositivos móveis:** Smartphone e tablet
- [ ] **Resoluções:** 1920x1080, 1366x768, 375x667
- [ ] **Sistema operacional:** Windows, macOS, Linux, iOS, Android

---

## 🚨 **ÁREA: USUÁRIOS ESPECIAIS**

### Pontos-Chave a Observar:

#### ⚠️ **problem_user**

- [ ] **Imagens:** Podem estar trocadas ou incorretas
- [ ] **Funcionalidades:** Possíveis bugs no fluxo normal
- [ ] **Layout:** Elementos podem estar desalinhados

#### 🐌 **performance_glitch_user**

- [ ] **Lentidão:** Carregamentos podem ser mais demorados
- [ ] **Timeout:** Observe se há falhas por tempo limite
- [ ] **Experiência:** Documente impacto na usabilidade

#### 🔒 **locked_out_user**

- [ ] **Bloqueio persistente:** Não deve conseguir acessar nunca
- [ ] **Mensagem clara:** Erro deve explicar o motivo do bloqueio

---

## 📊 **CHECKLIST DE OBSERVAÇÕES GERAIS**

### Durante todo o teste exploratório, observe:

#### 🔍 **Console do Desenvolvedor**

- [ ] Erros JavaScript
- [ ] Warnings ou alertas
- [ ] Requisições com falha (4xx, 5xx)
- [ ] Recursos não carregados

#### 🎯 **Experiência do Usuário**

- [ ] Intuitividade da interface
- [ ] Clareza das mensagens
- [ ] Facilidade de navegação
- [ ] Tempo para completar tarefas

#### 📱 **Acessibilidade**

- [ ] Contraste de cores adequado
- [ ] Tamanho de fonte legível
- [ ] Navegação por teclado
- [ ] Textos alternativos em imagens

---

## 💡 **DICAS PARA EXPLORAÇÃO EFETIVA**

1. **🎲 Seja imprevisível:** Teste fluxos não convencionais
2. **🔄 Teste limites:** Valores máximos, mínimos, vazios
3. **📱 Mude contexto:** Diferentes dispositivos e orientações
4. **⏱️ Varie o timing:** Cliques rápidos, lentos, simultâneos
5. **🧪 Combine ações:** Use diferentes funcionalidades em sequência
6. **📋 Documente tudo:** Screenshots, steps to reproduce, ambiente

---

## 📝 **TEMPLATE PARA REPORTAR BUGS**

```
🐛 **Título:** [Descrição concisa do problema]

🔍 **Descrição:** 
[Explicação detalhada do que aconteceu]

📋 **Passos para reproduzir:**
1. [Passo 1]
2. [Passo 2]
3. [Passo 3]

✅ **Resultado esperado:**
[O que deveria acontecer]

❌ **Resultado atual:**
[O que realmente acontece]

🌐 **Ambiente:**
- Browser: [Chrome 118.0]
- OS: [Windows 11]
- Resolução: [1920x1080]
- Usuário: [standard_user]

📷 **Evidências:**
[Screenshots, vídeos, logs]

⚠️ **Severidade:**
[ ] Crítica [ ] Alta [ ] Média [ ] Baixa
```

---

## 👨‍💻 Autor

**Danillo Silva**

- GitHub: [@Danillosdd](https://github.com/Danillosdd)
- Projeto: [projetos-de-conclusao](https://github.com/Danillosdd/projetos-de-conclusao)

## 📄 Licença

Este projeto é parte dos estudos de **Formação em Teste de Software da Iterasys** e demonstra boas práticas em teste exploratório e heurísticas de teste com nomenclatura completamente em português brasileiro.

Todas as heurísticas fornecem orientações práticas para identificação sistemática de problemas durante testes exploratórios no e-commerce SauceDemo.
