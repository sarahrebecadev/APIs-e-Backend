# Sprint 3 — DTOs e consultas

Projeto Spring Boot independente para a atividade de DTOs e consultas de alunos.

## Funcionalidades
- `POST /alunos`: recebe `AlunoRequest` e retorna `AlunoResponse` com status 201.
- `GET /alunos`: lista os alunos.
- `GET /alunos/{id}`: consulta por ID e retorna 404 quando não encontrado.

## Configuração
Configure `BANCO_URL`, `BANCO_USUARIO` e `BANCO_SENHA` para o PostgreSQL. O padrão de URL é `jdbc:postgresql://localhost:5432/academia`.

Execute com `mvn spring-boot:run` dentro desta pasta.