# Atividade 1: Projeto Java para desktop
*Retirado do roteiro de aula do Prof. Flávio Horita, CMCC/UFABC.*

## 1. Instalando o Eclipse IDE

Baixe o Eclipse **IDE for Java EE Developers** de acordo com o sistema
operacional da sua máquina e descompacte o arquivo em alguma pasta.

Para executar, entre na pasta **eclipse** e clique duas vezes em
`eclipse.exe`. Na primeira vez que executar, será solicitado o diretório
de trabalho (*Workspace*), que é a pasta onde ficarão armazenados os seus
projetos criados no Eclipse.

PS: Verifique se o JDK está instalado no computador e configurado como
variável de ambiente. Para isso, basta rodar na linha de comando:
`java -version`. Caso não estiver instalado, baixe e instale a versão
atual.

Após instalado, rode o comando novamente e veja o retorno. Se ainda assim,
não funcionar. Configure a variável de ambiente pelo seguinte passo-a-passo
(Windows):

- Clique com o botão direito em cima do ícone "Meu Computador";
- Vá em "Propriedades";
- Selecione a aba "Configurações avançadas do sistema", depois na aba
  "Avançado";
- Clique no botão "Variáveis de ambiente";
- Clique no botão "Nova" em "Variáveis do sistema";
  - Nome da variável: `JAVA_HOME`
  - Valor da variável: Endereço de instalação
- Clique em OK.

Passo-a-passo (Linux):

- Abrir o arquivo `bash.bashrc`
  - `vim /etc/bash.bashrc`
- Incluir as seguintes linhas:
  
  ```bash
  export JAVA_HOME=/opt/java
  export PATH=/opt/java/bin:$PATH
  ```
- Testar

  ```console
  $ echo $JAVA_HOME
  $ echo $PATH
  ```

## 2. Primeiro Programa no Eclipse

Vamos criar o nosso primeiro projeto Java no Eclipse.

1. Para criar um novo projeto, selecione: **File → New → Java Project**. 
   Dê o nome ao projeto de `PrimeiroProjeto` e clique em **Finish**.
2. Abra o projeto na janela à esquerda (*package explorer*) e crie uma classe:
   clique direito sobre o pacote `src`, e selecione a opção **New → Class**
   e dê o nome de `Principal`. Selecione a opção 
   `public static void main (String[] args)` para gerar o método principal
   e clique em **Finish**.

Caso tenha esquecido de selecionar a opção para criar o método principal, basta
digitar a palavra `main` dentro da classe criada e pressionar `Ctrl + barra de 
espaço`, que o método principal será gerado automaticamente.

3. Digite um comando para imprimir uma frase como "Primeiro Projeto!".
4. Para salvar o programa, pressione `Ctrl + S` ou selecione **File → Save**.
5. Para executar o programa, clique no botão **Run** (seta verde) ou clique
   direito sobre o projeto e selecione a opção **Run As → Java Application**
   ou pressione `Ctrl + F11`.

## 3. Projeto Java para desktop 1

Para relembrar um pouco da orientação a objetos em Java, vamos desenvolver
alguns projetos Java para desktop.

1. Crie um projeto Java para desktop (opção **File → New → Java Project**).
   Dê o nome ao projeto de `ContaCorrente` e clique em **Finish**.
2. Crie um pacote chamado `br.com.bb.contacorrente.modelo` da seguinte forma:
   clique direito sobre a pasta `src`, opção **New → Package**.
3. Dentro do pacote recém-criado, crie uma classe chamada `ContaCorrrente`
   e inclua os seguintes atributos: 
   
   ```java
   private Long id;
   private String numero;
   private String agencia;
   private String descricao;
   private boolean ativa;
   private int variacao;
   ```

4. Observe que criamos o atributo `id` do tipo `Long` não do tipo `long`, 
   porque `Long` inicia o atributo com valor default `null`. Fazemos assim
   para não dar erro na inserção do banco de dados.
5. Vamos transformar a classe `ContaCorrente` em um *javabean*. *Javabeans*
   são classes que possuem o construtor sem argumentos e métodos de acesso do
   tipo *get* e *set*. Para fazer isso de forma automática no Eclipse, menu Source e selecione **Generate Getters and Setters**. Na janela que abrir,
   selecione todos os atributos (ou clique em Select All).
6. Crie um pacote chamado `br.com.bb.contacorrente.testes`: clique direito 
   sobre a pasta `src`, opção **New → Package**.
7. Dentro do pacote recém-criado, crie uma classe chamada `CriaContaCorrente`: 
   clique direito sobre o pacote, opção **New → Class**. A classe deve ter o
   seguinte código:

   ```java
   package br.edu.ufabc.contacorrente.testes;

   import br.edu.ufabc.contacorrente.modelo.ContaCorrente;
   
   public class CriaContaCorrente {
     public static void main(String[] args) {
       ContaCorrente cc = new ContaCorrente();
       cc.setNumero("1234-0");
       cc.setAgencia("9876");
       cc.setDescricao("Conta corrente do Banco do Brasil");
       cc.setAtiva(true);
       cc.setVariacao(0);
       cc.setId((long) 3);

       System.out.println(cc.getNumero());
       System.out.println(cc.getAgencia());
       System.out.println(cc.getDescricao());
       System.out.println(cc.isAtiva());
       System.out.println(cc.getVariacao());
       System.out.println(cc.getId());
     }
   }
   ```

Agora crie um vetor do tipo `ContaCorrente` e armazene dados de várias contas.
Faça um laço de repetição para listar os dados das contas armazenadas no vetor.
Inclua textos como: "Conta número: xxx", "Agência: " etc.

## 4. Projeto Java para desktop 2

Crie um novo projeto Java e inclua as seguintes classes, com os respectivos 
atributos e métodos:

- **Classe:** `Funcionário`

  **Atributos:** `nome`, `cpf`, `status`, `setor`, `salario mensal`

  **Métodos:** calcula o ganho anual
- **Classe:** `Empresa`
  
  **Atributos:** `nome`, `cnpj`, `funcionarios` (utilize um `ArrayList`*)

  **Métodos:** insere um funcionário na lista, lista todos os funcionários
  (nome e cpf), lista todos os funcionários de um setor, lista todos os 
  funcionários com status "ativo", busca por um funcionário pelo `cpf`
  (retorna um Funcionario).

Lembre-se de declarar os atributos como "privado" (utilizando os tipos de dados
adequados) e criar métodos públicos para acesso (*getters*/*setters*) quando 
necessário. Para cada classe, escreva um construtor para atribuir os valores 
aos atributos (todos os atributos para classe `Funcionario` e somente
os atributos `nome` e `cnpj` para a classe `Empresa`, além da inicialização do
`ArrayList`).

Escreva o método principal em outra classe e crie um objeto empresa e pelo 
menos 5 funcionários para testar os métodos. Você pode apresentar opções para
o usuário (para ver informações da empresa e dos funcionários, utilizando os
métodos criados) e solicitar uma opção para ser executada.

(*) `ArrayList` é uma classe da biblioteca Java para trabalhar com coleções de 
objetos (listas), onde o tamanho da lista pode variar dinamicamente. Possui 
métodos para tarefas comuns como, por exemplo, inserir, remover elementos, 
tamanho da lista etc.

O `ArrayList` é uma classe genérica: `ArrayList<T>` contém referências para
objetos do tipo `T`. Exemplo:

```java
ArrayList<String> nomes = new ArrayList<>();
```
