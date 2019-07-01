# Atividade 5: Spring
*Retirado do roteiro de aula do Prof. Flávio Horita, CMCC/UFABC.*

## 1. Spring Boot e a Geração de Projetos

A automatização na criação de projetos empregando SpringBoot e SpringMVC
pode ser realizada por meio do seguinte site: https://start.spring.io/

<img align="center" src="https://user-images.githubusercontent.com/14254807/60460418-17916c80-9c1a-11e9-83c3-00f6cc96c370.png" />

1. No site.
2. Project Metadata
   1. *Group*: refere-se ao pacote organizador do nosso projeto; sem o nome
      do projeto em si (por exemplo, `br.edu.ufabc` ou `br.com.bb`).
   2. *Artifact*: nome do nosso projeto.
3. Dependencies
   1. JPA: inclui no arquivo `build.gradle` as dependências do Hibernate.
   2. Web: inclui no arquivo `build.gradle` as dependências do SpringMVC.
   3. Postgres: inclui no arquivo `build.gradle` as dependências do Postgres.

## 2. Criar um projeto Spring MVC

1. **Criar** um novo projeto `SistemaBancarioMVC` utilizando o sistema de
   automatização para Spring Boot, conforme descrito acima.
2. **Executar** as instruções no Gradle para importar as dependências e, 
   em seguida, **importar** o projeto no eclipse.
   1. Caso você tenha incluído o Postgres como dependência do projeto, é
      necessário configurar o acesso ao banco de dados. Para isso, abra o
      arquivo `application.properties` dentro da pasta `src/main/resources`
      e insira as configurações:

      ```properties
      spring.jpa.show-sql=true
      spring.jpa.hibernate.ddl-auto=update
      spring.datasource.driverClassName=org.postgresql.Driver
      spring.datasource.url=jdbc:postgresql://localhost:5432/<nome_banco>
      spring.datasource.username=<usuario>
      spring.datasource.password=<senha>
      ```

      Atente-se: para alterar o nome do banco de dados, usuário e senha.

   2. Para inicializar o projeto, execute a instrução `gradle bootRun` na
      linha de comando. Se houver sucesso no processo a seguinte mensagem
      deve ser exibida na linha de comando: "*Started <nome do projeto> in
      XXX seconds*".
   3. Em seguida, digitar no navegador `https://localhost:8080` e uma
      página em branco deve aparecer, ou uma página com a mensagem
      "*This application has no explicit mapping for /error, so you 
      are seeing this as a fallback*". Verifique na linha de comando
      se não houve nenhuma mensagem de erro.
   4. Para parar o projeto, digite <kbd>Ctrl + C</kbd> na linha de comando e,
      em seguida, <kbd>Y</kbd> e <kbd>Enter</kbd>.
3. **Criar** os pacotes do modelo MVC, ou seja, 
   `br.com.bb.sistemabancario.model.entity`,
   `br.com.bb.sistemabancario.model.dao`,
   `br.com.bb.sistemabancario.controller`,
   `br.com.bb.sistemabancario.service`.
4. **Implementar** a operação de listar conta corrente.
   1. Você deve usar, principalmente, as seguintes anotações do Spring 
      MVC para incluir as classes no escopo da aplicação.
      1. `@Controller`
      2. `@Repository`
      3. `@Service`
      4. `@RequestMapping(value = "<ref_url>")`
   2. Além disso, para evitar o acoplamento por instanciação, utilize a 
      Inversão de Controle e Injeção de Dependências com a anotação 
      `@Autowired`.
   3. Por fim, o DAO tem que ser implementado como uma interface e herdar 
      as características da classe 
      `JpaRepository<_classe_, _tipo atributo id_>`, onde `classe` é
      a classe em si e o `tipo` pode ser `Long`, `Integer` etc. Exemplo:

      ```java
      package br.com.bb.SistemaBancarioMVC.model.dao;

      import org.springframework.data.jpa.repository.JpaRepository;
      import org.springframework.stereotype.Repository;

      import br.com.bb.SistemaBancarioMVC.model.entity.ContaCorrente;

      @Repository
      public interface ContaCorrenteDao extends JpaRepository<ContaCorrente, Long> {

      }

## 3. Desafios

- **Implementar** a interface para a operação de listar conta corrente: 
  - Dica 1: utilizar a dependência: 
    
    ```gradle
    compile 'org.springframework.boot:spring-bootstarter-thymeleaf'
    ```
  - Dica 2: as interfaces/páginas HTML devem ficar dentro da pasta 
    `/resources/templates/`
  - Dica 3: O retorno do controle deve ser uma instância da classe
    `ModelAndView`
- Implementar a interface para a operação de cadastrar nova conta corrente:
  - Dica 1: o formulário não precisa ser em outra página. Pode utilizar a 
    instrução de código abaixo.

    ```html
    <form action="save" method="post">
      <div class="form-group">
        <label for="nome">Numero</label>
        <input type="text" class="form-control" id="numero" name="numero" 
          placeholder="numero" />
      </div>
      <div class="form-group">
        <label for="email">Descricao</label>
        <input type="text" class="form-control" id="descricao" name="descricao"
          placeholder="descricao" />
      </div>
      <div class="form-group">
        <label for="ativa">Ativa</label>
        <input type="radio" name="ativa" value="true">Ativa<br>
        <input type="radio" name="ativa" value="false">Desativa<br>
      </div>
      <div class="form-group">
        <label for="telefone">Variacao</label>
        <input type="text" class="form-control" id="variacao" name="variacao"
          placeholder="variacao" />
      </div>
      <button type="submit" class="btn btn-success">Salvar</button>
    </form>
    ```
  - Dica 2: o valor da `action` no formulário deve ter um valor de 
    referência no `value` do controller.
  - Dica 3: para receber os valores do formulário pelo método POST, 
    utilize a anotação `@RequestParam("<nome ref. do campo no html>")`, 
    antes do tipo do parâmetro no método do controller.
