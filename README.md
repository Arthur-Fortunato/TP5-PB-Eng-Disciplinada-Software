[![CI/CD Pipeline](https://github.com/Arthur-Fortunato/TP5-PB-Eng-Disciplinada-Software/actions/workflows/ci.yml/badge.svg)](https://github.com/Arthur-Fortunato/TP5-PB-Eng-Disciplinada-Software/actions/workflows/ci.yml)

# CRUD de Produtos v2

Projeto simples em **Java utilizando Maven** que implementa um CRUD de produtos com arquitetura em camadas.

## Estrutura do projeto

* **entity** → classe que representa o produto
* **repository** → persistência em memória
* **service** → regras de negócio e validações
* **controller** → comunicação entre o menu e o service
* **util** → interface de execução do sistema

## Build do projeto

Para compilar o projeto:
**mvn clean package**

## CI (GitHub Actions)

O repositório tem um workflow configurado no GitHub Actions que executa automaticamente o build do projeto.

O pipeline roda quando existe:

* push na branch **main**
* criação de **pull request**

Durante a execução o GitHub cria um ambiente Linux e executa:

**mvn clean package**

Isso garante que o projeto sempre compile corretamente.