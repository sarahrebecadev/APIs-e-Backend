# Sprint 4 — PUT, DELETE e Lombok

Projeto Spring Boot independente com DTOs, atualização e exclusão de alunos.

## Endpoints
- `POST /alunos`: cria aluno (201).
- `GET /alunos`: lista alunos.
- `GET /alunos/{id}`: busca por ID (404 se não existir).
- `PUT /alunos/{id}`: atualiza nome e idade (404 se não existir).
- `DELETE /alunos/{id}`: exclui aluno (204; 404 se não existir).

A entidade usa Lombok. Configure `BANCO_URL`, `BANCO_USUARIO` e `BANCO_SENHA` para PostgreSQL e execute `mvn spring-boot:run` nesta pasta.