# Sistema de Gerenciamento de Pedidos (SGP)

## 🚀 Sobre o Projeto

Este projeto é um **Sistema de Gerenciamento de Pedidos (SGP)** desenvolvido inteiramente em **Linguagem Java**. Ele foi criado como uma solução robusta, modular e leve para gerenciar de forma automatizada o ciclo de registro e consulta de clientes, produtos e vendas de uma organização , rodando diretamente como uma aplicação desktop.

O sistema utiliza a biblioteca **Java Swing** para construir uma **Interface Gráfica de Usuário (GUI)** intuitiva e organizada , oferecendo formulários, tabelas e botões de ação que facilitam a usabilidade.

A arquitetura do projeto segue rigorosamente o padrão **MVC (Model-View-Controller)** integrado a uma camada de **Repositório** dedicada. A persistência dos dados (salvamento) é gerenciada de forma estruturada através de **arquivos texto formatados em CSV (.csv)**, utilizando escrita atômica para garantir a consistência das informações e permitindo a recuperação total dos dados ao reiniciar o programa.

---

## 📦 Principais Funcionalidades

* O sistema foi projetado de forma modular, dividido em pacotes de responsabilidade única:


**Módulo de Clientes:** Permite o cadastro e a manutenção da base de clientes.


* **CRUD Completo:** Funções para incluir (com ID sequencial sugerido), alterar o nome, excluir (respeitando vínculos) e consultar clientes por ID ou nome parcial.


* **Validação de Dados:** Impede a inserção de nomes vazios e garante o controle de chaves únicas.



**Módulo de Produtos:** Controla as mercadorias disponíveis para venda com gestão de estoque.


* **CRUD Completo:** Inclusão, alteração de dados (nome, preço e estoque) e exclusão via código identificador.


* **Integridade Comercial:** Bloqueia preços ou quantidades negativas no inventário.



**Módulo de Pedidos e Itens:** O coração do sistema, responsável por associar clientes a múltiplos produtos.

 
* **Validação de Estoque:** Impede que a quantidade de itens comprados exceda a quantidade disponível no estoque do produto.


* **Baixa Automática:** Deduz do estoque na adição de itens e devolve/incrementa os produtos automaticamente caso um item ou pedido seja alterado ou excluído.


* **Cálculo e Recálculo Automático:** Calcula na hora o preço total do item e o valor total geral do pedido (soma dos itens), sem permitir edição manual.


* **Integridade Referencial:** Impede a exclusão de clientes ou produtos que possuam vínculos ativos em pedidos em andamento.


* **Manutenção de Arquivo Seguro:** Utiliza escrita atômica (salva as alterações primeiro em um arquivo temporário e depois o renomeia) para evitar a corrupção do CSV caso o sistema falhe.





## 📋 Pré-requisitos

Para compilar e executar este projeto, você precisará ter instalado em sua máquina:

* **Java Development Kit (JDK) 11** ou superior.

* **Java Runtime Environment (JRE) 11** ou superior configurado nas variáveis de ambiente.

* Uma IDE de sua preferência (como *NetBeans*, *IntelliJ IDEA* ou *Eclipse*) ou o terminal de comandos.


## 🔧 Compilação e Organização

O código fonte está estruturado em pacotes organizados por responsabilidade:

```text
src/
├── model/         # Classes de Domínio (Cliente, Produto, Pedido, Item)
├── view/          # Interfaces Gráficas Swing (PedidoView, ClienteView, etc.)
├── controller/    # Intermediadores da lógica de negócio (Controllers)
├── repository/    # Responsáveis pelo acesso e persistência em arquivos CSV
└── util/          # Classes utilitárias (ArquivoUtil para FileLock/Escrita, Validador)

```

### Compilação via Terminal

Para compilar o projeto manualmente a partir da pasta raiz do código fonte (`src/`), execute:

```bash
javac -d bin model/*.java view/*.java controller/*.java repository/*.java util/*.java Main.java

```

---

## ▶️ Execução

Após a compilação bem-sucedida, os arquivos da aplicação serão gerados na pasta de destino (ex: `bin/`). Para iniciar a aplicação gráfica, execute:

```bash
java -cp bin Main

```

> 💡 **Nota:** Na primeira inicialização, o sistema criará automaticamente o diretório relativo `./data/` para armazenar de forma segura os arquivos `clientes.csv`, `produtos.csv`, `pedidos.csv` e `itens.csv`.
> 
> 

---

## 👥 Autores

  - Daniel Sobrinho Mendes (Sistemas de Informação - Universidade Estadual de Goiás)
  - Enzo Oliveira Finotti (Sistemas de Informação - Universidade Estadual de Goiás)
  - Gabriel Prado Menezes (Sistemas de Informação - Universidade Estadual de Goiás)
