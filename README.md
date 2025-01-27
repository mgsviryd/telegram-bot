# telegram-bot 🚀
Completing of OnTravelSolutions assignment

---

## Introduction
- use `Java 17`
- create a `Spring Boot 3` project
- implement `REST` controllers
- attach `JPA` repositories
- connect to `MySQL` datasource
- generate `ngrok` public URL
- containerize an application using `Docker`

---

## Task
- ./Тестовое задание.docx

---

## Features
- Create Telegram bot, respond to user requests in real-time.

---

## Environment

### Mac OS
| Name                                                | Description                                                                                                                                                                        |
|-----------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [brew](https://brew.sh/)                            | Manager to install and control versions of packages, e.g. `jdk` and `maven`<br/> `/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"` |
| [nano](https://brew.sh/)                            | Command-line editor<br/> `brew install nano`                                                                                                                                       |
| [jdk](https://www.java.com/)                        | Java Development Kit to manage and run java programs<br/>`brew install openjdk@17`<br/>`java -version`                                                                             |
| [maven](https://maven.apache.org/)                  | Software for management pom.xml (dependencies, plugins ...)<br/>`brew install maven@3.9.9`<br/>`maven -version`                                                                    |
| [mysql](https://dev.mysql.com/downloads/installer/) | Database management system<br/>`brew install mysql`                                                                                                                                |
| [git](https://git-scm.com/)                         | Version control system<br/>`brew install git`                                                                                                                                      |
| [docker](https://www.docker.com/)                   | Platform designed to help developers build, share, and run container applications<br/>`brew install docker`                                                                        |
| [ngrok](https://dashboard.ngrok.com/)                    | Forwarding your host to public URL<br/>`brew install ngrok`<br/>Following the installation instructions add your authtoken to the default ngrok.yml.                               |

### PC
| Name                                                 | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
|------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [chocolatey](https://chocolatey.org/)                | Manager to install and control versions of packages, e.g. `jdk` and `maven`<br/>`/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"<br/>Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))` |
| [nano](https://chocolatey.org/)                      | Command-line editor<br/>`choco install nano`                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [zip](https://community.chocolatey.org/packages/zip) | Provider of high-quality versions of the Zip and UnZip compressor-archiver utilities<br/>`choco install zip`                                                                                                                                                                                                                                                                                                                                                      |
| [sdkman](https://sdkman.io/)                         | Software Development Kit Manager<br/>`curl -s "https://get.sdkman.io"`                                                                                                                                                                                                                                                                                                                                                                                            |
| [jdk](https://www.java.com/)                         | Java Development Kit to manage and run java programs<br/>`sdk ls java`<br/>`sdk i java 17.0.12-jbr`                                                                                                                                                                                                                                                                                                                                                               |
| [maven](https://maven.apache.org/)                   | Software for management pom.xml (dependencies, plugins ...)<br/>`sdk ls maven`<br/>`sdk i maven 3.9.9`                                                                                                                                                                                                                                                                                                                                                            |
| [mysql](https://dev.mysql.com/downloads/installer/)  | Database management system<br/>`choco install mysql`                                                                                                                                                                                                                                                                                                                                                                                                              |
| [git](https://git-scm.com/)         | Version control system<br/>`choco install git -y`                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [docker](https://www.docker.com/)                   | Platform designed to help developers build, share, and run container applications<br/>`choco install docker`                                                                                                                                                                                                                                                                                                                                                      |
| [ngrok](https://dashboard.ngrok.com/)                    | Forwarding your host to public URL<br/>`choco install ngrok`<br/>Following the installation instructions add your authtoken to the default ngrok.yml.                                                                                                                                                                                                                                                                                                             |

---

## Pre-setup

### 1. Telegram bot configuration
You can create your Telegram bot using [@BotFather](https://telegram.me/BotFather).

  Bot configuration:
- username: `mgsviryd_bot`
- token: `7574109082:AAHfhyqSe5ywP7Tf6AGASiFHFBhmHsuBCUE`
> ⚠️ Keep token secured in your real project.


### 2. Clone git repository
```shell
git clone https://github.com/mgsviryd/telegram-bot.git    
```

### 3. Go to directory
```shell
cd telegram-bot
```

### 4. Create .env file and set environment variables
```shell
nano .env
```
Paste and edit <your_db_name>, <your_mysql_username>, <your_mysql_password>):

```
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/<your_db_name>?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true
SPRING_DATASOURCE_USERNAME=<your_mysql_username>
SPRING_DATASOURCE_PASSWORD=<your_mysql_password>

TELEGRAM_BOT_USERNAME=mgsviryd_bot
TELEGRAM_BOT_TOKEN=7574109082:AAHfhyqSe5ywP7Tf6AGASiFHFBhmHsuBCUE
```

### 5. Check your ports
By default, we use next ports:
- 3306 - for mysql (must be open),
- 8080 - for backend (must be close),
```shell
nc -zv localhost 3306
```
**Status:** ✅ Success
```shell
nc -zv localhost 8080
```
**Status:** 🚫 Refused

### 6. Start ngrok to receive domain
```shell
ngrok http http://localhost:8080
```

Now http://localhost:8080 forwarding to **domain**:

Example:

> https://784b-2001-41d0-700-7611-00.ngrok-free.app


You can create ngrok [stable domain](https://dashboard.ngrok.com/domains). And use it:

Example:
```shell
ngrok http http://localhost:8080 --url=mature-outgoing-alpaca.ngrok-free.app
```
---

## Setup (via command-line)
⚠️ first complete [Pre-setup](#pre-setup)

### 1. Setup project version of maven (locally)
```shell
mvn -N io.takari:maven:wrapper
```

### 2. Setup packages and build .jar file
```shell
export $(cat .env | xargs) && mvn clean install
```

### 3. Run
```shell
mvn spring-boot:run
```
### 4. Done

✅ The application is complete and running. To check application scroll [here](#check).

---

## Setup (via Docker)
⚠️ first complete [Pre-setup](#pre-setup)

### 1. Start docker daemon
On a typical installation the Docker daemon is started by a system utility. For more information see [here](https://docs.docker.com/engine/daemon/start/).

### 2. Build docker image
```shell
docker build -t your-image-name -f Dockerfile .
```

### 3. Build docker container and run on port your port (here: 8081)
```shell
docker run --name your-container-name -p 8080:8080 your-image-namе
```
### 4. Done

✅ The application is complete and running. To check application scroll [here](#check)

---

## Application Testing
### 1. Add city using REST request
```shell
curl -X POST  https://mature-outgoing-alpaca.ngrok-free.app/cities \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{
  "name": "Москва",
  "info": "Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))"
}'
```

### 2. Test Telegram bot
#### 1. Find bot in Telegram:

`@mgsviryd_bot`  

#### 2. Send message to bot:

`Москва`

#### 3. Check answer:

`Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))`

### 3. Inspect ngrok http requests   

http://localhost:4040/inspect/http

---

## Terminate (via command-line)
### 1. Use keyboard shortcut 
`CTRL + C`

---

## Terminate (via Docker)
### 1. Stop docker container
```shell
docker stop your-container-namе
```

### 2. Remove docker container
```shell
docker rm your-container-namе
```
### 3. Remove docker image
```shell
docker rmi your-image-namе
```
---
