# Atividade 2: Conectando com o banco de dados
*Retirado do roteiro de aula do Prof. Flávio Horita, CMCC/UFABC.*

## 1. Instalação do Banco de Dados (SGBD)

Para os exercícios das aulas, você poderá utilizar um dos bancos de dados
(SGBD) abaixo:
- Postgres (https://www.postgresql.org/download/)
- H2 (Java SQL Database) (https://h2database.com/)

## 2. Criando um banco de dados para a aplicação

Vamos criar um banco de dados para a aplicação do exercício da aula passada
(projeto `SistemaBancario`). Para maiores detalhes, siga as instruções do
guia de instalação com o banco de dados que está utilizando.

1. Inicie o programa do banco de dados. Crie um banco de dados chamado
   `SistemaBancario`.

   No Postgres utilize o comando:

   ```pgsql
   create database "SistemaBancario" with
     owner = postgres
     encoding = 'UTF8'
     connection limit = -1;
   ```

2. Crie uma tabela chamada `ContaCorrente` com as seguintes colunas:
   `id`, `numero`, `agencia`, `descricao`, `ativa` e `variacao`.

   No Postgres utilize:

   ```pgsql
   create table public."ContaCorrente" (
     id serial not null,
     numero character varying(255) not null,
     agencia character varying(255) not null,
     descricao character varying(255) not null,
     ativa boolean not null,
     variacao integer not null,
     primary key (id)
   );
   ```

3. Faça alguns testes utilizando os comandos SQL para inserir, alterar,
   remover registros no banco de dados.

   ```pgsql
   insert into "ContaCorrente"(numero, agencia, descricao, ativa, variacao)
   values ('1222-3', '333', 'Conta Corrente do Banco do Brasil', true, 0);
   ```

   Altere o status `ativo` para `false` do registro cujo `id = 1`:

   ```pgsql
   update "ContaCorrente" set ativa = false where id = 1;
   ```

4. Após cada comando, verifique o conteúdo do banco de dados para certificar
   que o comando foi executado corretamente (utilize um `select` para listar
   os registros).

## 3. Incluindo o driver JDBC

Para que uma aplicação possa acessar o banco de dados, é preciso que o driver
JDBC seja incluído no projeto (para isso basta indicar a localização do driver
(arquivo `.jar`) no *classpath* do projeto, conforme é explicado a seguir). 
Para adicionar o driver ao projeto, clique direito sobre o projeto e selecione
**Build Path → Configure Build Path → Libraries → Add External JARs** e selecione o arquivo `.jar` do driver.

Teste a conexão com o banco de dados, utilize a classe abaixo:

```java
public class TestaConexao {
  public static void main(String[] args) {
    Connection conexao = null;
    try {
      String url = "jdbc:postgresql://localhost/SistemaBancario";
      conexao = DriverManager.getConnection(url, "root", "root");
      // Para o H2 descomente as linhas abaixo e comente as linhas acima.
      // String url = "jdbc:h2:tcp://localhost/~/progweb";
      // conexao = DriverManager.getConnection(url, "admin", "admin");
      System.out.println("Conectou!");
    } catch (SQLException e1) {
      System.out.println("Erro ao abrir a conexao" + e1.getMessage());
    } finally {
      try {
        conexao.close();
      } catch (SQLException e2) {
        System.out.println("Erro ao fechar a conexão" + e2.getMessage());
      }
    }
  }
}
```

## 4. Conectando a aplicação com o banco de dados

Vamos utilizar o projeto `SistemaBancario` da aula passada para exemplificar 
como conectar com o banco de dados usando o JDBC.

1. No projeto `SistemaBancario`, crie um pacote que será responsável pelas 
   conexões com o BD: `br.com.bb.sistemabancario.jdbc`.
2. No pacote `br.com.bb.sistemabancario.jdbc`, crie uma classe chamada 
   `ConnectionFactory` com o código:

   ```java
   public class ConnectionFactory {
     public Connection getConnection(){
       System.out.println("Conectando ao banco de dados");
       try {
         String url = "jdbc:postgresql://localhost/SistemaBancario";
         return DriverManager.getConnection(url, "root", "root");
         // Para o H2: descomente a parte abaixo e comente as duas linhas acima.
         // String url = "jdbc:h2:tcp://localhost/~/progweb"
         // return DriverManager.getConnection(url, "admin", "admin");
       } catch (Exception e) {
         throw new RuntimeException(e);
       }
     }
   }
   ```

3. No projeto `SistemaBancario`, crie também um pacote que será responsável 
   por objetos de acessos aos dados (DAO - *Data Access Object*):
   `br.com.bb.sistemabancario.dao`.
5. No pacote `br.com.bb.sistemabancario.dao`, crie a classe `ContaCorrenteDAO`;
6. Lembre-se de importar `java.sql` (cuidado para não confundir com a classe
   `com.sql`); Atalho para importar bibliotecas faltantes: `Ctrl + Shift + O`.
7. Coloque a conexão com o banco de dados no construtor do `ContaCorrenteDAO`:

   ```java
   public class ContaCorrenteDAO {
     private Connection connection;

     public ContaCorrenteDAO() {
       this.connection = new ConnectionFactory().getConnection();
     }
   }
   ```

8. Implemente a inserção de registros:

   ```java
   public void insere(ContaCorrente cc) {
     String sql = "insert into \"ContaCorrente\" "
         + "(numero, agencia, descricao, ativa, variacao) "
         + "values (?, ?, ?, ?, ?);";

     try { 
       // Prepared statement para inserção.
       PreparedStatement stmt = connection.prepareStatement(sql);
       // Define os valores.
       stmt.setString(1, conta corrente.getNumero());
       stmt.setString(2, conta corrente.getAgencia());
       stmt.setString(3, conta corrente.getDescricao());
       stmt.setBoolean(4, true);
       stmt.setInteger(5, conta corrente.getVariacao());
       // Executa.
       stmt.execute();
       // Fecha o statement.
       stmt.close();
     } catch (SQLException e) {
       throw new RuntimeException(e);
     }
   }
   ```

9. Modifique a classe principal `CriaContaCorrente`, acrescentando um código 
   para testar o método de inserção:

   ```java
   // Gravando registro.
   ContaCorrenteDAO dao = new ContaCorrenteDAO();
   dao.insere(cc);
   System.out.println("Gravado!");
   ```

## 5. Operações adicionais

1. Implemente as outras operações: remoção, alteração e listagem de registros 
   na classe `ContaCorrenteDAO` e teste os métodos na classe 
   `CriaContaCorrente`. Veja os exemplos nos slides da aula teórica.
2. Faça uma verificação do BD após cada operação.
3. Observe que a remoção é feita pelo `id` da conta corrente e, portanto, 
   é necessário conhecê-lo de antemão. Para obter o `id`, pode-se inicialmente
   fazer uma busca para recuperar o registro do BD, neste caso pelo nome da conta corrente. Ou seja, crie um novo método em `ContaCorrenteDAO` para 
   buscar um registro pelo nome da conta corrente:

   ```java
   public ContaCorrente buscaPeloNome(String nome) {...}
   ```