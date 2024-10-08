## Desafio: Biblioteca

Projeto que visa desenvolver um sistema bibliotecario.

***
### Estrutura do Projeto

O projeto é organizado nas seguintes camadas:

- **Controller**: Manipula as requisições HTTP e retornar as respostas apropriadas.
- **Entity**: Representa as entidades do sistema (Usuario, Livro, Emprestimo) e suas relações.
- **DTO (Data Transfer Object)**: Transferir dados entre as camadas.
- **Service**: Contém a lógica de negócios e regras de validação.
- **Repository**: Camada de acesso a dados, responsável por interagir com o banco de dados.
---
### Funcionalidades

- **Cadastro de Usuários**: Permite adicionar novos usuários ao sistema.
- **Cadastro de Livros**: Permite adicionar novos livros ao sistema.
- **Empréstimos**: Gerencia o empréstimo e a devolução de livros pelos usuários.
- **Validação**: Implementação de validação para garantir a integridade dos dados.
- **Testes Unitários**: Para garantir que as funcionalidades estejam funcionando.

---
### Tecnologias Utilizadas

- **Java**: Linguagem de programação.
- **Spring Boot**: Framework para desenvolvimento das aplicações Java.
- **JPA e Hibernate**: Para manipulação do banco de dados.
- **PostgreSQL**: Banco de dados para armazenar os dados.
- **JUnit e Mockito**: Para construção de testes unitários.
- **Swagger**: Para escrever a documentação necessaria.(http://localhost:8080/swagger-ui/index.html)
- **Postman**: Para testar as requisões HTTP.
- **Angular**: Para o desenvolvimento das telas Front-end. (http://localhost:4200/).

---
### Instruções

### Configuração do Banco de Dados

1. **Instale o PostgreSQL**: Siga as instruções do site oficial para instalar o PostgreSQL.


2. **Crie um Banco de Dados**:
    - Acesse o terminal do PostgreSQL ou use um cliente gráfico como pgAdmin.
    - Crie um banco de dados com o seguinte comando:
      ```sql
      CREATE DATABASE biblioteca;
      ```
3. **Configuração do `application.properties`**:

    - Navegue até o diretório do seu projeto Spring e edite o arquivo `src/main/resources/application.properties` 
    - para incluir as seguintes configurações:

      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
      spring.datasource.username=seu_usuario
      spring.datasource.password=sua_senha
      spring.jpa.hibernate.ddl-auto=update
      ```

### Executando a Aplicação

### Backend

1. Navegue até o diretório do seu projeto backend.
2. Execute o seguinte comando para iniciar o servidor:
   ```bash
   mvn spring-boot:run