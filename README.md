# Produto Service 📦

Este projeto é uma aplicação que fornece informações de produtos através de duas formas de consulta:

- **Consulta por Filtro:** Permite buscar produtos com base em parâmetros específicos.  
- **Consulta por ID:** Permite obter detalhes de um produto individual, buscando pelo seu identificador único.  

A aplicação é construída com **Java 17**, utiliza **Maven** para gerenciamento de dependências, **Spring Boot** para estruturação do backend, e **WireMock** para simulação de APIs externas. O banco de dados é simulado com um arquivo JSON e o cache é mantido em memória utilizando o próprio Spring.

---

## 🚀 Rodando a Aplicação Localmente
O projeto está disponibilizado em um arquivo ZIP. Para rodá-lo localmente, considere que ele foi extraído diretamente na raiz do seu C:\. Recomendo usar o mesmo local para extrair e conseguir seguir as informações abaixo.
 
### ⚙️ Configurando Variáveis de Ambiente no Windows

As variáveis de ambiente dizem ao sistema **onde encontrar o Java e o Maven**, necessários para compilar e rodar o projeto.

---

#### 1️⃣ Abrir a configuração de variáveis de ambiente

1. Pressione `Win + S` e digite **variáveis de ambiente**.  
2. Clique em **“Editar as variáveis de ambiente do sistema”**.  
3. Na janela **Propriedades do Sistema**, clique em **Variáveis de Ambiente**.  

---

#### 2️⃣ Criar a variável `JAVA_HOME`

1. Em **Variáveis do Sistema**, clique em **Novo**.  
2. Preencha:  
   - **Nome da variável:** `JAVA_HOME`  
   - **Valor da variável:** caminho da instalação do Java 17:  
     ```
     C:\opt\dev\tools\java\jdk-17
     ```  
3. Clique em **OK**.  

---

#### 3️⃣ Criar a variável `MAVEN_HOME`

1. Ainda em **Variáveis do Sistema**, clique em **Novo**.  
2. Preencha:  
   - **Nome da variável:** `MAVEN_HOME`  
   - **Valor da variável:** caminho da instalação do Maven:  
     ```
     C:\opt\dev\tools\maven\apache-maven-3.9.11
     ```  
3. Clique em **OK**.  

---

#### 4️⃣ Adicionar Java e Maven ao `PATH`

1. Na lista de **Variáveis do Sistema**, selecione a variável `Path` e clique em **Editar**.  
2. Clique em **Novo** e adicione as seguintes entradas:
%JAVA_HOME%\bin
%MAVEN_HOME%\bin
3. Clique em **OK** em todas as janelas para salvar.  

#### 5️⃣ Testar se funcionou

Abra um terminal (Prompt de Comando ou PowerShell) e digite:  

```bash
java -version
mvn -v
Se estiver tudo certo, você verá a versão do Java e do Maven instalados.
```
### 🐳 Instalando e Rodando o Docker no Windows

O Docker será usado para rodar o **WireMock**, simulando APIs externas para o projeto.

---

#### 1️⃣ Instalar Docker Desktop

1. Baixe o **Docker Desktop** a partir do site oficial:  
   [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)  
2. Execute o instalador e siga as instruções.  
3. Após a instalação, abra o **Docker Desktop** e verifique se está rodando.  

---

#### 2️⃣ Rodar o WireMock com Docker
Execute o seguinte comando para iniciar o WireMock:
```bash
docker run -d --name wiremock -p 8081:8080 -v C:\opt\dev\project\product-service\wiremock:/home/wiremock/mappings wiremock/wiremock
```
#### 1️⃣ Rodar o Spring Boot
No terminal, dentro do diretório do projeto:
```bash
mvn clean install
mvn spring-boot:run
```
