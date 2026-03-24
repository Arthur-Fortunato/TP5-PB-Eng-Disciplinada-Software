[![CI/CD Pipeline](https://github.com/Arthur-Fortunato/TP5-PB-Eng-Disciplinada-Software/actions/workflows/ci.yml/badge.svg)](https://github.com/Arthur-Fortunato/TP5-PB-Eng-Disciplinada-Software/actions/workflows/ci.yml)

# CRUD de Produtos v2

Projeto desenvolvido em **Java com Spring Boot e Maven**, implementando CRUD de produtos com foco em clean code, testes automatizados e CI.

---

## Arquitetura do projeto

O foi arquitetado em camadas:

- **entity** → modelo de domínio
- **repository** → persistência em memória
- **service** → regras de negócio
- **controller** → API REST
- **dto** → transferência de dados (request/response)

---

## Testes

O projeto possui diferentes níveis de testes:

- Testes unitários (na camada de serviço)
- Testes de integração (no controller usando MockMvc)
- Testes end-to-end usando Selenium (interface)

---


## CI/CD (GitHub Actions)

O pipeline automatizado realiza:

-  Build do projeto
-  Execução de testes automatizados
-  Testes usando Selenium
-  Geração de relatório de cobertura
-  Upload de artefatos

### Gatilhos

- Push na branch **main**
- Pull Request
- Execução manual (`workflow_dispatch`)

---

## Ambientes

O pipeline faz uma simulação do deploy em 3 ambientes:

- **dev**
- **test**
- **prod**

O ambiente de produção possui aprovação manual antes do deploy

---

## Como executar

- `mvn clean install`
- `mvn spring-boot:run`