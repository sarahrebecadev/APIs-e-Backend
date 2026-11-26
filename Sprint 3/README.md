# Sprint 3 — DTOs e CRUD completo

## Objetivo
Concluir uma API REST de alunos com Spring Boot, separando os objetos recebidos e enviados pela API da entidade persistida no banco.

## Implementações
- `AlunoRequest`: representa os dados recebidos no POST e PUT.
- `AlunoResponse`: representa os dados devolvidos pela API, sem expor diretamente a Entity.
- `POST /alunos`: cria um aluno e responde com HTTP 201.
- `GET /alunos/{id}`: consulta por ID e responde com HTTP 200.
- `PUT /alunos/{id}`: atualiza o cadastro e responde com HTTP 204.
- `DELETE /alunos/{id}`: verifica a existência e remove, respondendo HTTP 204.
- Camadas separadas de Controller, Service, Repository, DTO e Entity.
- Uso explícito de `Optional.isPresent()` e `if/else`, sem lambda na consulta e nas verificações desta sprint.

## Tecnologias
Java 21, Spring Boot 3.4.5, Spring Web, Spring Data JPA, Maven e H2 Database.

## Executar
Na pasta Sprint 3:
```bash
mvn spring-boot:run
```
A API inicia em `http://localhost:8080`. O H2 é usado em memória para facilitar a execução local, sem exigir instalação de PostgreSQL.

## Testes rápidos
Criar:
```http
POST http://localhost:8080/alunos
Content-Type: application/json

{"nome":"Ana","idade":21}
```
Consultar: `GET http://localhost:8080/alunos/1`

Atualizar:
```http
PUT http://localhost:8080/alunos/1
Content-Type: application/json

{"nome":"Ana Souza","idade":22}
```
Remover: `DELETE http://localhost:8080/alunos/1`

> Nesta etapa, erros de ID inexistente são sinalizados pela camada de serviço com exceção explícita. A padronização global das respostas de erro é desenvolvida na Sprint 5.
