# JavaFX CRUD — Produtos do Restaurante

Aplicação desktop em JavaFX para **cadastrar (Create)** e **consultar (Retrieve)** produtos de um restaurante. O projeto utiliza MVC, Java Persistence API (JPA) com Hibernate ORM e banco H2 local.

## Entidade escolhida

A entidade `Produto` é abstrata e possui duas especializações: `Comida` e `Bebida`. Os atributos apresentados são ID, nome, descrição, preço, categoria e tipo. O mapeamento usa herança `SINGLE_TABLE`: as subclasses são persistidas na tabela `produtos`, diferenciadas pela coluna `tipo_produto`.

## Tecnologias

- Java 21
- JavaFX 21 (FXML)
- Hibernate ORM 6.6 / Jakarta Persistence
- H2 Database (arquivo local)
- Maven

## Como executar

1. Instale o JDK 21 e o Maven.
2. Abra a pasta do projeto na IDE (IntelliJ IDEA, por exemplo) como projeto Maven.
3. Aguarde o Maven baixar as dependências.
4. Execute no terminal, na raiz do projeto:

   ```bash
   mvn clean javafx:run
   ```

5. A primeira execução cria automaticamente a pasta `data` e o banco `restaurante` (pode ser criado no primeiro acesso). O Hibernate cria/atualiza a tabela com `hibernate.hbm2ddl.auto=update`.

## Estrutura

```text
src/main/java/br/com/restaurante/
├── Main.java
├── model/
│   ├── Produto.java
│   ├── Bebida.java
│   └── Comida.java
├── repository/
│   └── ProdutoRepository.java
├── persistence/
│   └── HibernateUtil.java
└── controller/
    └── ProdutoController.java

src/main/resources/view/
└── produto-view.fxml
```

## MVC e persistência

- **Model:** `Produto`, `Comida` e `Bebida`.
- **View:** `produto-view.fxml`, com formulário e `TableView`.
- **Controller:** `ProdutoController`, que valida os campos, chama o repositório e atualiza a tabela.
- **Repository:** `ProdutoRepository`, responsável pelas operações de consulta e inserção.
- **ORM:** Hibernate mapeia as entidades Java para a tabela do banco H2.

## Funcionalidades implementadas

- Consulta de todos os produtos persistidos.
- Cadastro de comida ou bebida.
- Validação de campos obrigatórios e preço.
- Atualização da `TableView` após cadastro.
- Persistência entre execuções por meio do banco H2 em arquivo.
