# vr-autorizador-api

[![Build](https://img.shields.io/github/actions/workflow/status/matcarv/vr-autorizador-api/main.yml?branch=main)](https://github.com/matcarv/vr-autorizador-api/actions)
[![Coverage](https://img.shields.io/badge/Coverage-93%25-brightgreen)](target/site/jacoco/jacoco.html)
[![SonarCloud](https://img.shields.io/badge/SonarCloud-Passed-brightgreen)](https://sonarcloud.io/summary/new_code?id=matcarv_vr-autorizador-api)

<!-- Shields das tecnologias usadas -->
[![Java](https://img.shields.io/badge/JAVA-ED8B00?logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/BOOT-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/MAVEN-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/DOCKER-2496ED?logo=docker&logoColor=white)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/SWAGGER-85EA2D?logo=swagger&logoColor=white)](https://swagger.io/)
[![SonarCloud](https://img.shields.io/badge/SONAR-F3702A?logo=sonarcloud&logoColor=white)](https://sonarcloud.io/)
[![Jacoco](https://img.shields.io/badge/JACOCO-25A162?logo=jacoco&logoColor=white)](https://www.jacoco.org/)

API de autorização de cartões VR Benefícios.

## Build & Testes

```bash
mvn clean package
mvn clean test
```

## Execução

```bash
docker compose build
docker compose up -d
```

## Documentação

- Swagger: [http://yourhost:8080/autorizador/v1/swagger-ui.html](http://yourhost:8080/autorizador/v1/swagger-ui.html)  
  Usuário: `admin` | Senha: `vr@123`

## SonarCloud

Dashboard: https://sonarcloud.io/project/overview?id=matcarv_vr-autorizador-api
