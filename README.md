# README - Aplicação JSF com JDK 11, Tomcat 8.5 e JPA (PostgreSQL)
# Visão Geral
Este repositório contém uma aplicação web construída com JavaServer Faces (JSF), usando o JDK 21 e implantada no servidor Tomcat 8.5. A aplicação é configurada para usar Java Persistence API (JPA) e Hibernate como provedor de persistência, com um banco de dados PostgreSQL.

# Pré-requisitos
Antes de começar, certifique-se de que você tenha as seguintes ferramentas instaladas:

- JDK 11: Certifique-se de que o JDK 21 esteja instalado e configurado em sua máquina.
Para Windows:
https://download.oracle.com/java/21/latest/jdk-21_windows-x64_bin.exe

Para Linux:
https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb

Para Mac:
https://download.oracle.com/java/21/latest/jdk-21_macos-x64_bin.dmg

- Tomcat 8.5: Faça o download e instale o Tomcat 8.5.
Para Windows:
https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.95/bin/apache-tomcat-8.5.95-windows-x64.zip

- Git: Caso você queira clonar este repositório.

- PostgreSQL: Certifique-se de ter o PostgreSQL instalado e em execução localmente.

# Implantação
- Clone este repositório para sua máquina:
https://github.com/miguel220/cadastro-usuario.git

- Abra sua IDE (por exemplo, Eclipse, IntelliJ, NetBeans) e importe o projeto.

- Configure o servidor Tomcat em sua IDE, apontando para o diretório de instalação do Tomcat 8.5.

- Implante a aplicação no servidor Tomcat.

# Configuração do Banco de Dados
- Crie um banco de dados vazio no PostgreSQL com o nome "cadastro-usuario". Você pode usar a interface de linha de comando psql ou uma ferramenta gráfica como o pgAdmin.

- createdb cadastro-usuario

- Abra o arquivo src/main/resources/META-INF/persistence.xml na aplicação e ajuste as configurações de conexão com o banco de dados. Certifique-se de definir a URL, nome de usuário e senha corretos para o PostgreSQL.

- persistence.xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">

    <persistence-unit name="jpa-persistence" transaction-type="RESOURCE_LOCAL">

        <properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/cadastro-user"/>
            <property name="javax.persistence.jdbc.user" value="seu_usuario"/>
            <property name="javax.persistence.jdbc.password" value="sua_senha"/>
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>
            <!-- Configuração para atualização automática do esquema (use com cuidado em produção) -->
            <property name="hibernate.hbm2ddl.auto" value="update"/>
        </properties>
    </persistence-unit>
</persistence>


# Uso
- Navegue pela aplicação para testar suas funcionalidades. Você pode personalizar a aplicação de acordo com suas necessidades específicas. O JPA deve criar as tabelas automaticamente no banco de dados durante a implantação.
- Abra um navegador da web e acesse http://localhost:8080/cadastro-usuario/ para verificar se a aplicação está sendo executada corretamente.

# Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para melhorar e expandir esta aplicação. Basta fazer um fork deste repositório, criar um branch, fazer suas alterações e enviar um pull request.

Personalize as informações relevantes para a sua aplicação e ambiente de desenvolvimento. Espero que este README seja útil para você e sua equipe!





