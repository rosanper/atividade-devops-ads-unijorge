Atividade avaliativa da matéria de Devops do curso de Análise e Desenvolvimento de Sinstemas da Unijorge


# 🛒 ByteStore

> E-commerce de produtos eletrônicos desenvolvido com Java e Spring Boot, estruturado para aplicação de práticas DevOps.

## 📋 Sobre o Projeto

A **ByteStore** é uma aplicação backend de e-commerce voltada à comercialização de produtos eletrônicos. O projeto foi concebido com um escopo enxuto e modular, criando uma base sólida para evoluir através das práticas de DevOps — incluindo controle de versão, testes automatizados, integração contínua (CI), conteinerização e implantação (CD).

### 🎯 Objetivo Principal
Demonstrar conceitos de desenvolvimento de software moderno e cultura DevOps na prática, aplicando:
* Organização de código em camadas;
* Fluxo de versionamento colaborativo (*Trunk-Based Development*);
* Automação de build e testes unitários/integração;
* Geração de artefatos e pipelines de entrega.

---

## ⚙️ Funcionalidades Iniciais

1. **Cadastro e consulta de produtos:** Gerenciamento do catálogo de eletrônicos.
2. **Adição de itens ao carrinho:** Inclusão de produtos disponíveis ao carrinho de compras (incrementando a quantidade caso o item já exista).
3. **Cálculo automático do total:** Soma dinâmica dos itens baseada no preço unitário e quantidade.
4. **Consulta do carrinho:** Visualização detalhada dos produtos adicionados e do valor final da compra.

### 💡 Exemplo de Funcionamento

Ao adicionar os seguintes itens ao carrinho:

| Produto | Preço Unitário | Quantidade | Subtotal |
| :--- | :---: | :---: | :---: |
| Notebook | R$ 3.500,00 | 1 | R$ 3.500,00 |
| Mouse | R$ 100,00 | 2 | R$ 200,00 |
| Teclado | R$ 200,00 | 1 | R$ 200,00 |
| **Total** | — | **4 itens** | **R$ 3.900,00** |

---

## 🛠️ Tecnologias e Dependências

* **Linguagem:** Java 25
* **Framework:** Spring Boot
* **Gerenciador de Dependências:** Maven (`pom.xml`)
* **Principais Dependências:**
  * **Spring Web:** Criação dos endpoints RESTful.
  * **Spring Data JPA:** Persistência e comunicação com o banco de dados.
  * **H2 Database:** Banco de dados relacional em memória para desenvolvimento e testes.
  * **Lombok:** Redução de código *boilerplate* (getters, setters, construtores).
  * **Bean Validation:** Validação dos dados de entrada da API.
  * **Spring Boot Starter Test:** Suporte para testes unitários e de integração.

---

## 🏗️ Arquitetura e Estrutura do Repositório

O projeto segue as convenções do Spring Boot combinadas com a estrutura exigida pelo roteiro de práticas:

```text
ByteStore/
├── src/
│   ├── main/
│   │   ├── java/com/bytestore/
│   │   │   ├── ByteStoreApplication.java
│   │   │   ├── controller/          # Endpoints REST (ProdutoController, CarrinhoController)
│   │   │   ├── service/             # Regras de negócio (ProdutoService, CarrinhoService)
│   │   │   ├── repository/          # Acesso a dados (ProdutoRepository, CarrinhoRepository)
│   │   │   ├── entity/              # Modelos JPA (Produto, Carrinho, ItemCarrinho)
│   │   │   ├── dto/                 # Objetos de transferência de dados
│   │   │   └── exception/           # Tratamento global de exceções
│   │   └── resources/
│   │       └── application.properties # Configurações da aplicação e banco H2
│   └── test/
│       └── java/com/bytestore/      # Testes unitários e de integração (executados pelo Maven)
├── tests/
│   └── README.md                    # Documentação da estratégia, cenários e evidências de testes
├── docs/
│   ├── arquitetura.md               # Detalhamento das camadas e diagramas
│   └── git-workflow.md              # Guia de branches, PRs e commits
├── .gitignore
├── pom.xml
└── README.md
```

### Fluxo entre Camadas
```text
[Cliente HTTP] ➔ [Controller] ➔ [Service] ➔ [Repository] ➔ [Banco H2]
```

### Modelo de Entidades
* **`Produto`:** `id`, `nome`, `descricao`, `preco`, `quantidadeEstoque` *(opcional na v1)*.
* **`Carrinho`:** `id`, `itens` (lista de `ItemCarrinho`), `valorTotal` (calculado dinamicamente).
* **`ItemCarrinho`:** `id`, `produto`, `quantidade`, `precoUnitario`.

O cálculo do valor total do carrinho é realizado dinamicamente pelo serviço através da fórmula:

$$Total = \sum (PrecoUnitario \times Quantidade)$$

---

## 👥 Divisão de Responsabilidades e Backlog

Para simular o trabalho colaborativo em equipe, o desenvolvimento foi dividido em duas frentes de atuação:

### 🔹 Frente 1: Rodrigo 1 — Domínio de Produtos e Estrutura Base
* **Responsabilidade:** Configurar o projeto inicial, estrutura de pacotes e todo o fluxo de cadastro/consulta de produtos.
* **Entregas:** `ByteStoreApplication`, `Produto`, `ProdutoRepository`, `ProdutoService`, `ProdutoController`.

### 🔹 Frente 2: Rodrigo 2 — Domínio de Carrinho e Regras de Cálculo
* **Responsabilidade:** Implementar a gestão do carrinho de compras, associação com os produtos cadastrados e cálculo do valor final.
* **Entregas:** `Carrinho`, `ItemCarrinho`, `CarrinhoRepository`, `CarrinhoService`, `CarrinhoController`.

### 📌 Backlog Inicial

| ID | Atividade | Responsável |
| :---: | :--- | :---: |
| **01** | Criar repositório Git e estrutura inicial | Rodrigo 1 |
| **02** | Criar projeto Spring Boot | Rodrigo 1 |
| **03** | Configurar dependências e banco H2 | Rodrigo 1 |
| **04** | Criar estrutura de pacotes | Rodrigo 1 |
| **05** | Criar entidade `Produto` | Rodrigo 1 |
| **06** | Criar repositório, serviço e controlador de `Produto` | Rodrigo 1 |
| **07** | Criar entidade `Carrinho` | Rodrigo 2 |
| **08** | Criar entidade `ItemCarrinho` | Rodrigo 2 |
| **09** | Criar repositório e serviço de `Carrinho` | Rodrigo 2 |
| **10** | Implementar adição e incremento de produtos ao carrinho | Rodrigo 2 |
| **11** | Implementar cálculo dinâmico do valor total | Rodrigo 2 |
| **12** | Criar controlador do `Carrinho` | Rodrigo 2 |
| **13** | Integrar as funcionalidades via Pull Requests | Rodrigo 1 e 2 |
| **14** | Documentar o projeto (`README.md` e `docs/`) | Rodrigo 1 e 2 |

---

## 🌿 Estratégia de Versionamento (Git)

O projeto adota o **Trunk-Based Development**, mantendo a branch `main` sempre estável e utilizando branches curtas para o desenvolvimento de funcionalidades:

* `main`: Branch principal e estável.
* `feature/produtos`: Desenvolvimento da estrutura base e CRUD de produtos (Rodrigo 1).
* `feature/carrinho`: Desenvolvimento do carrinho e cálculo de totais (Rodrigo 2).

### Fluxo de Trabalho
1. Inicializar o repositório na branch `main`.
2. Criar a branch `feature/produtos`, desenvolver e abrir um **Pull Request** para a `main`.
3. Após revisão e merge, criar a branch `feature/carrinho` a partir da `main` atualizada.
4. Desenvolver as funcionalidades do carrinho e abrir um **Pull Request** para integrar à `main`.

### Padrão de Commits
Seguimos a especificação *Conventional Commits*:

| Tipo | Finalidade | Exemplo |
| :--- | :--- | :--- |
| `feat` | Nova funcionalidade | `feat(produto): adicionar entidade produto` |
| `fix` | Correção de bug | `fix(carrinho): corrigir calculo do valor total` |
| `docs` | Documentação | `docs(readme): adicionar instrucoes de execucao` |
| `test` | Testes automatizados | `test(produto): criar testes unitarios do service` |
| `refactor` | Refatoração de código | `refactor(carrinho): otimizar busca de itens` |
| `chore` | Tarefas de configuração/build | `chore(maven): configurar dependencias iniciais` |

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
* **JDK 25** instalado.
* **Maven 3.8+** instalado (ou utilizar o Maven Wrapper `./mvnw`).
* **Git** instalado.

### Passo a passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/ByteStore.git
   cd ByteStore
   ```

2. **Compile o projeto e baixe as dependências:**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Execute os testes automatizados:**
   ```bash
   mvn test
   ```

A aplicação estará disponível em `http://localhost:8080` e o console do banco H2 poderá ser acessado em `http://localhost:8080/h2-console` (conforme configurado no `application.properties`).

