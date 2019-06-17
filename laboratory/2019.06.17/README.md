# Atividade 3: Hibernate
*Retirado do roteiro de aula do Prof. Flávio Horita, CMCC/UFABC.*

## 1. Adicionando o Hibernate/JPA ao Projeto

Vamos utilizar o projeto Sistema Bancário da aula passada como base para 
ilustrar a utilização do  Hibernate, e modificar o acesso ao banco de dados.

Crie um novo projeto web dinâmico chamado `SistemaBancarioHibernate` e baixe 
as libs necessárias para o Hibernate.

Para incluir os pacotes, clique com o botão direito em cima do projeto e, 
em seguida, *Properties*. Selecione a opção *Java Build Path* e, na aba 
*Libraries*, clique em *Add External JARs*. Inclua todos os JARs do 
arquivo descompactado.

## 2. Criando uma entidade

Crie um pacote `br.com.bb.sistemabancario.model` e copie a classe
`ContaCorrente` das aulas passadas.

Inclua as anotações do JPA:

```java
package br.com.bb.sistemabancario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contacorrente")
public class ContaCorrente {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name="agencia")
  private String agencia;

  @Column(name="numero")
  private String numero;

  @Column(name="descricao")
  private String descricao;

  ...
}
```

Não esqueça de gerar os *getters* e *setters*!

Como visto em aula, a anotação `@Entity` indica que a classe `ContaCorrente` 
é uma entidade (que será persistida pelo Hibernate). Toda classe (POJO – 
*Plain Old Java Object*) que representa uma entidade precisa ter essa 
anotação.

A anotação `@Table` permite definir a tabela em que a entidade será 
persistida. É opcional, se não estiver definida, o nome da tabela é o mesmo
nome da classe da entidade.

A anotação `@Id` indica que o campo `id` é a chave primária da tabela e 
`@GeneratedValue` significa que o `id` será gerado pelo JPA (por exemplo, 
`AUTO INCREMENT` do Postgres).

A anotação `@Column` é usada para mapear uma coluna da tabela a um campo da
classe. É opcional, se não estiver definida, por padrão o nome do campo é 
o nome da coluna. Outros possíveis atributos: `nullable`, `length`, `unique`
etc.

Observe que os *imports* são do pacote `javax.persistence`.

Veja maiores detalhes sobre as anotações em: 
https://docs.oracle.com/javaee/7/api/, pacote `javax.persistence` 
(*Annotation Types*).

## 3. Configurando o Hibernate/JPA (`persistence.xml`)

Para configurar o Hibernate/JPA é preciso criar um arquivo chamado 
`persistence.xml`. Este arquivo contém informações sobre sobre a implementação
JPA (provedor), as classes (entidades) que serão mapeadas, além de propriedades
do banco de dados (*driver*, URL de conexão, usuário e senha) e propriedades 
do Hibernate.

Crie uma nova pasta em `src` chamada `META-INF` e dentro desta, crie o 
arquivo XML (`persistence.xml`) e inclua o conteúdo abaixo:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
    http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
  version="2.1">
  <persistence-unit name="sistemabancario">
    <!-- Implementação do JPA (provedor). -->
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <!-- Entidade mapeada. -->
    <class>br.com.bb.sistemabancario.model.ContaCorrente</class>
    <properties>
      <property name="javax.persistence.jdbc.driver"
        value="org.postgresql.Driver" />
      <property name="javax.persistence.jdbc.url"
        value="jdbc:postgresql://localhost/SistemaBancarioHibernate" />
      <property name="javax.persistence.jdbc.user"
        value="postgres" />
      <property name="javax.persistence.jdbc.password" 
        value="postgres" />
      <property name="hibernate.dialect"
        value="org.hibernate.dialect.PostgreSQLDialect" />
        
      <!-- Conexão H2. -->
      <!-- <property name="javax.persistence.jdbc.driver" 
             value="org.h2.Driver" /> -->
      <!-- <property name="javax.persistence.jdbc.url"
             value="jdbc:h2:tcp://localhost/~/progwebjpa" /> -->
      <!-- <property name="javax.persistence.jdbc.user" 
             value="admin" /> -->
      <!-- <property name="javax.persistence.jdbc.password" 
             value="admin" /> -->
      <!-- <property name="hibernate.dialect" 
             value="org.hibernate.dialect.H2Dialect"/> -->
     
      <!-- Imprime as queries SQL no console. -->
      <property name="hibernate.show_sql" value="true" />
      <property name="hibernate.format_sql" value="true" />
      <!-- Gera as tabelas se necessário. -->
      <property name="hibernate.hbm2ddl.auto" value="update" />
    </properties>
  </persistence-unit>
</persistence>
```

Não esqueça de trocar o nome do banco, usuário e senha para acessar o 
banco de dados.

*Obs.:* Se estiver utilizando o H2, comente no código acima as propriedades
da conexão do Postgres e descomete a parte do H2.

## 4. Gerando tabelas no banco de dados

Primeiramente, crie um novo banco de dados chamado `sistemabancariohibernate` 
no banco de dados que você utiliza, Postgres ou H2.

Baseado nas anotações (da entidade) e no arquivo `persistence.xml`, o 
Hibernate é capaz de gerar automaticamente a tabela no banco de dados. 
Para isso, vamos criar um novo pacote `br.com.bb.sistemabancario.dao` e 
dentro deste, crie a classe `GeradorTabelas`, com o seguinte
conteúdo:

```java
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeradorTabelas {
  public static void main(String[] args) {
    EntityManagerFactory factory = Persistence
        .createEntityManagerFactory("sistemabancario");
    System.out.println("Tabela gerada!");
    factory.close();
  }
}
```

No código acima, a classe `EntityManagerFactory` abre a conexão com o banco 
de dados, buscando as propriedades definidas no arquivo `persistence.xml`. 
No método `createEntityManagerFactory` é passado como parâmetro o nome da 
unidade de persistência (que foi definida no arquivo `persistence.xml`) e 
se a tabela não existir esta é criada.

Execute o programa: clique direito sobre o nome da classe e selecione 
*Run as → Java Application*.

Entre no Postgres/H2 e verifique que a tabela contacorrente foi gerada.

## 5. Acessando o banco de dados

Vamos acessar o banco de dados e realizar operações utilizando o Hibernate/JPA.

Para salvar um objeto `ContaCorrente` no banco de dados, utilizaremos o 
seguinte código no método `insere` da classe `ContaCorrenteDao`:

```java
public String insere(ContaCorrente cc) {
  EntityManagerFactory factory = Persistence
      .createEntityManagerFactory("sistemabancario");
  EntityManager manager = factory.createEntityManager();

  try {
    manager.getTransaction().begin();
    manager.persist(cc);
    manager.getTransaction().commit();
  } finally {
    if (manager.getTransaction().isActive())
      manager.getTransaction().rollback();
  }
  manager.close();
}
```

A classe `EntityManager` oferece métodos para executar as operações 
(inserção, alteração, remoção e consulta) no banco de dados e uma instância 
é obtida através do objeto `EntityManagerFactory`.

Para salvar um objeto `ContaCorrente` no banco de dados, basta chamar o 
método `persist` do `EntityManager` passando o objeto. Operações que 
modificam o conteúdo do banco de dados devem ser executados dentro de uma
transação. Uma transação é gerenciada pelo objeto `EntityTransaction`,
através do `EntityManager`.

## 6. Operações adicionais

1. Implemente as outras operações: remoção, alteração e listagem de registros
   na classe `ContaCorrenteDAO`. Na aula passada, implementamos a classe 
   `CriaContaCorrente` para testar as operações de inserção, remoção, 
   alteração e listagem. Essa classe não precisa ser modificada, pois as
   alterações foram feitas no DAO, logo, você pode executar essa classe da
   mesma forma como ela está implementada.
2. Faça uma verificação do BD após cada operação.
3. Crie um método para listar todas as contas correntes utilizando o trecho 
   de código abaixo: 

   ```java
   public String listAll() {
     EntityManagerFactory factory = Persistence
         .createEntityManagerFactory("sistemabancario");
     EntityManager manager = factory.createEntityManager();
     
     List<ContaCorrente> ccs = manager
         .createQuery("select cc from ContaCorrente cc", ContaCorrente.class)
         .getResultList();

     manager.close();
     return ccs;
   }
   ```

   Para a busca, utilizamos uma consulta JPQL (*JPA Query Language*), que é uma
   linguagem que possui uma sintaxe semelhante à linguagem SQL. A principal 
   diferença é que a JPQL trabalha com classes e objetos, enquanto que a SQL 
   trabalha com tabelas, registros e linhas do banco de dados relacional.

4. Observe que a remoção é feita pelo `id` da conta corrente e, portanto, é 
   necessário conhecê-lo de antemão. Para obter o `id`, pode-se inicialmente
   fazer uma busca para recuperar o registro do BD, neste caso pelo nome da
   conta corrente. Ou seja, crie um novo método em `ContaCorrenteDAO` para
   buscar um registro pelo nome da conta corrente:

   ```java
   public ContaCorrente buscaPeloNome(String nome) {...}
   ```

5. Implemente outras duas consultas no banco de dados: 

   1. Busca por número de conta e agência;
   2. Busca por descrição (utilize o `LIKE` – procure referências no JPQL).

6. Por fim, modifique o método save (inserção de conta corrente no banco de
   dados) para manipular tanto com a inserção de um objeto `ContaCorrente`
   quanto com sua alteração. Em outras palavras, os comandos `persist` e
   `merge` estarão no mesmo método.
