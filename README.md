# vr-autorizador-api

[![Build](https://img.shields.io/github/actions/workflow/status/matcarv/vr-autorizador-api/main.yml?branch=main)](https://github.com/matcarv/vr-autorizador-api/actions)
[![Coverage](https://img.shields.io/badge/Coverage-93%25-brightgreen)](target/site/jacoco/jacoco.html)
[![SonarCloud](https://img.shields.io/badge/SonarCloud-Passed-brightgreen)](https://sonarcloud.io/summary/new_code?id=matcarv_vr-autorizador-api)

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
