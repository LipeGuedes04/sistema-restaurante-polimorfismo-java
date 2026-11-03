# 🍔 Motor de Vendas e Checkout de Fast-Food - ByteBite Engine

## 🎯 Escopo do Sistema

O sistema simula o motor core de autoatendimento, bilhetagem de comandas e conciliação financeira de pedidos para redes de fast-food e hamburguerias inteligentes (ByteBite). Rompendo com designs engessados de dados primitivos, a aplicação adota uma arquitetura avançada baseada em **Classes Abstratas e Polimorfismo por Contrato**. O operador gerencia uma coleção dinâmica em memória RAM capaz de unificar o ciclo de vida de pedidos distintos através de uma superclasse abstrata de domínio (`ItemPedido`). A plataforma permite registrar comandas, discernir taxas adicionais customizadas baseadas em modificadores físicos de produtos — como **acréscimos volumétricos para bebidas de tamanho grande** ou **taxas estruturais para hambúrgueres de carne dupla** —, além de processar auditorias completas e transições automáticas de status em tempo real.

### 🔄 Fluxo de Processamento e Regras Fiscais
```text
[ Input via Terminal ] ➔ [ Instanciação Abstrata ] ➔ [ Cálculo Polimórfico (+R$4/+R$8) ] ➔ [ Injeção por Composição ] ➔ [ Checkout/Faturamento ]
```

---

## 🎨 Arquitetura do Software (Abstração & Contratos POO)

O design deste ecossistema demonstra alto nível de engenharia ao aplicar o desacoplamento estrito. A classe abstrata define um contrato matemático que delega a responsabilidade de precificação final às regras de negócio de cada subclasse especializada.

```text
========================================================================================
  DIAGRAMA DE ARQUITETURA ABSTRATA E COMPOSIÇÃO (RAM)
========================================================================================

                 ┌───────────────────────────┐
                 │       «Abstract»          │
                 │       ItemPedido          │ (Define Contrato de Interface)
                 ├───────────────────────────┤
                 │ - nome: String            │
                 │ # precoBase: double       │
                 ├───────────────────────────┤
                 │ + calcularPrecoFinal()*   │◄────────────────────┐
                 └─────────────▲─────────────┘                     │
                               │                                   │
               ┌───────────────┴───────────────┐                   │
               │                               │ (Herança)         │
     ┌─────────┴─────────┐           ┌─────────┴─────────┐         │ (Invocação
     │    Hamburguer     │           │      Bebida       │         │  Polimórfica)
     ├───────────────────┤           ├───────────────────┤         │
     │ - duplo: boolean  │           │ - tamanho: String │         │
     └───────────────────┘           └───────────────────┘         │
               ▲                               ▲                   │
               └───────────────┬───────────────┘                   │
                               │ (Ponteiro Genérico por Contrato)   │
                               ▼                                   │
     ┌────────────────────────────────────────────────────────┐    │
     │                        Pedido                          │    │
     ├────────────────────────────────────────────────────────┤    │
     │  - numeroPedido: int                                   │    │
     │  - status: StatusPedido (Enum: PREPARANDO/ENTREGUE)    │    │
     │  - item: ItemPedido (Composição) ──────────────────────┼────┘
     └────────────────────────────────────────────────────────┘
```

### 📋 Inventário de Componentes e Responsabilidades

| Componente Técnico | Tipo | Atuação e Responsabilidade no Sistema |
| :--- | :--- | :--- |
| 🚀 `Main.java` | Controlador | Orquestra a interface com o usuário por console, o loop principal `while` e implementa os barramentos de busca defensiva por ID. |
| 📦 `Pedido.java` | Entidade Core | Agregadora central do domínio. Vincula o identificador do pedido ao status de fluxo e delega a renderização textual ao item composto. |
| 👑 `ItemPedido.java` | Classe Abstrata | Superclasse base que bloqueia instanciações genéricas incorretas e força contratualmente a existência de algoritmos de preços nos filhos. |
| 🍔 `Hamburguer.java` | Subclasse | Extensão especializada que herda as propriedades base e implementa o gatilho de valor incremental (+ R\$ 8.00) caso o item seja duplo. |
| 🥤 `Bebida.java` | Subclasse | Extensão especializada que avalia o buffer de tamanho textual e aplica acréscimos percentuais de insumos (+ R\$ 4.00) para o porte grande. |
| ⚙️ `StatusPedido.java` | Enum | Tipo enumerado responsável pelo controle normativo e máquina de estados das comandas (`PREPARANDO`, `ENTREGUE`). |

---

## 🧠 Conceitos Avançados Praticados

* **Classes Abstratas e Métodos Sem Corpo (`abstract`):** Definição de moldes corporativos que impedem a criação de objetos incompletos no sistema, garantindo consistência estrutural no domínio.
* **Polimorfismo Baseado em Regras Fiscais:** Flexibilidade onde uma mesma assinatura de método (`calcularPrecoFinal`) se ramifica em equações matemáticas completamente diferentes baseadas no estado interno do objeto filho.
* **Visibilidade Protegida (`protected`):** Uso estratégico de modificadores de acesso para permitir que atributos da superclasse sejam lidos e modificados de forma direta por suas subclasses sem expor os dados para o restante da aplicação.
* **Operador Ternário de Renderização Textual:** Implementação de lógica condicional inline compacta `((duplo) ? "Sim" : "Não")` para customizar as saídas de logs comerciais sem inflar o código com estruturas `if-else` tradicionais.

---

## 💻 Demonstração Operacional (Terminal)

Veja a simulação analítica do console realizando o cadastro polimórfico de um combo duplo de hambúrguer e a conferência fiscal de fechamento por ID único:

```text
======= Menu de Pedidos ========
1 - Cadastrar Pedido
2 - Fechar Pedido
3 - Histórico de Pedidos
4 - Pesquisar pedido específico
5 - Sair do Programa
------------------------------------ 
Qual opção você deseja?
1
====== Cadastrar Pedido ========
1 - Hamburguer || 2 - Bebida
1
Qual nome do combo: Monster Bacon Smash
Insira o numero desse combo: 404
Insira o valor do combo: 32.00
O combo é duplo? (S/N): S
Pedido Cadastrado

======= Menu de Pedidos ========
1 - Cadastrar Pedido
2 - Fechar Pedido
3 - Histórico de Pedidos
4 - Pesquisar pedido específico
5 - Sair do Programa
------------------------------------ 
Qual opção você deseja?
4
======= Pesquisar Pedido Específico =======
Insira o número do pedido que deseja buscar: 404

 Pedido Encontrado:
Nome: Monster Bacon Smash| Preço Base: R\$ 32.0 | Duplo: Sim | Valor total: R\$ 40.0
Valor total deste pedido: R\$ 40.00

