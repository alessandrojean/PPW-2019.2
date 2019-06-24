# Atividade 4: Spring
*Retirado do roteiro de aula do Prof. Flávio Horita, CMCC/UFABC.*

## 1. Instalar e configurar o Gradle

1. **Realizar** o download do arquivo de instalação do Gradle no site:
   https://gradle.org/install/#manually
2. **Descompactar** o conteúdo em uma pasta de conhecimento, por exemplo, C:/Gradle
3. **Configurar** o Gradle como variável de ambiente:
   1. Windows: Meu Computador/Propriedades/Variáveis de Ambiente
   2. Linux: `export PATH=$PATH:/opt/gradle/gradle-4.8.1/bin`
4. Após a configuração, **realizar** um teste na linha de comando com a seguinte
   instrução:

   ```console
   $ gradle -v
   ```

## 2. Criar projetos com o Gradle

1. Depois de configurar o Gradle, na linha de comando **utilizar** as seguintes 
   instruções para interagir com a ferramenta:

   1. `gradle init --type java-application`: cria um projeto *java application*
   2. `gradle build`: "constrói" o projeto Gradle
   3. `gradle tasks`: apresenta a lista de tarefas disponívels para o Gradle
   4. `gradle eclipse`: configura o projeto para o ambiente do Eclipse
   5. `gradle run`: executa o arquivo Gradle.

2. Todas as instruções são definidas em um arquivo de configuração, chamado `build.gradle`,
   gerado após a execução da instrução `gradle init`.
3. Abaixo, segue um exemplo do arquivo de configuração:

   ```gradle
   plugins {
     // Apply the java plugin to add support for Java
     id 'java'

     // Apply the application plugin to add support for building an application
     id 'application'

     // Apply the eclipse plugin to add support for using Eclipse IDE
     id 'eclipse'
   }

   // Define the main class for the application
   mainClassName = 'App'

   dependencies {
     // This dependency is found on compile classpath of this component and consumers.
     compile 'com.google.guava:guava:23.0'
     // Use JUnit test framework
     testCompile 'junit:junit:4.12'

     // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
     compile group: 'org.hibernate', name: 'hibernate-core', version: '5.0.0.Final'

     // https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager
     compile group: 'org.hibernate', name: 'hibernate-entitymanager', version: '5.0.0.Final'
   }

   // In this section you declare where to find the dependencies of your project
   repositories {
     // Use jcenter for resolving your dependencies.
     // You can declare any Maven/Ivy/file repository here.
     jcenter()
   }
   ```

1. As duas partes principais do arquivo são: 1) dependencies onde você consegue acrescentar
   mais pacotes para seu projeto (por ex., jdbc, hibernate, etc) e 2) plugins onde você
   acrescenta plug-ins necessários para o seu ambiente de desenvolvimento (por ex., eclipse).

   1. Mais dependências são encontradas no site: https://mvnrepository.com/

2. **Criar** um projeto `SistemaBancarioGradle` utilizando o gradle.

   1. **Criar** uma pasta na *worskpace*
   2. **Acessar** a pasta e executar a instrução para criar projeto do Gradle
   
3. **Incluir as dependências** do hibernate e jdbc postgres no build.gradle
4. **Importar** no eclipse (gradle eclipse).

   1. Clique com botão direito no *Project Explorer, Import, Import, Existing Projects 
      into Workspace, Browse*. Procure a pasta onde o projeto foi criado.

5. **Implementar** apenas a operação de inserir uma conta corrente no banco de dados.

   1. OBS: A execução do projeto será por meio do comando `gradle run` ou g`radle bootRun`
      na linha de comando. Não mais pelo Eclipse.
   2. O arquivo persitence.xml deve ser incluído em uma pasta de projeto chamada
      `/src/main/resources/META-INF/` (criar a pasta no projeto criado).
   3. Se preferir, utilize os arquivos das aulas anteriores. 
