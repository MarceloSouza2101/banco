# Gestão de Pessoas e Transações Bancárias ♨️

Este projeto é uma aplicação que gerencia o **cadastro de pessoas físicas**, **criação de contas bancárias** e **realização de transações**. A aplicação interage com um banco de dados para armazenar informações de usuários e transações realizadas.

---

## 🚀 Rodando a Aplicação com Docker Compose

### 1. **Instalar Docker e Docker Compose**

- **Instalar Docker no Linux**

Se você já tem o Docker instalado, pode ser uma boa ideia remover versões anteriores para evitar conflitos:

```bash
sudo apt-get remove docker docker-engine docker.io containerd runc
```

Primeiro, instale os pacotes necessários para adicionar o repositório Docker:

```bash
sudo apt-get update
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
```

Adicione o repositório oficial do Docker para Ubuntu/Debian:

```bash
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
```
Atualize os pacotes disponíveis:

```bash
sudo apt-get update
```

Agora, instale a versão desejada do Docker:

```bash
sudo apt-get install docker-ce=5:26.0.2~3-0~ubuntu-$(lsb_release -cs) docker-ce-cli=5:26.0.2~3-0~ubuntu-$(lsb_release -cs) containerd.io
```

- **Instalar Docker Compose no Linux**
  
Abra o terminal e execute o seguinte comando para baixar a versão específica do Docker Compose:

```bash
sudo curl -L "https://github.com/docker/compose/releases/download/v2.29.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```

Após o download, é necessário garantir que o Docker Compose tenha permissões para ser executado. Execute:

```bash
sudo chmod +x /usr/local/bin/docker-compose
```

### 2. **Clonar o Repositório**

Clone este repositório para o seu ambiente local:

```bash
git clone https://github.com/seu-usuario/banco.git
cd banco
```

### 3. **Rodar o Docker Compose**

No diretório do projeto, execute o seguinte comando para construir e rodar a aplicação:

```bash
mvn clean package install -DskipTests
docker-compose up --build
```

Se você já tiver rodado o build anteriormente, pode rodar apenas:

```bash
docker-compose up
```

### 4. **Acessar a Aplicação**

Após a execução, sua aplicação estará disponível em:

```bash
http://localhost:8081
```
