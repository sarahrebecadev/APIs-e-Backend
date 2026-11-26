# APIs e Backend — TechX

Repositório de atividades práticas de desenvolvimento de APIs REST e backend com Java e Spring Boot.

## Organização
| Sprint | Conteúdo |
|---|---|
| [Sprint 1](Sprint%201/) | Primeiros passos com Spring Boot, JPA e entidade Curso. |
| [Sprint 2](Sprint%202/) | API inicial de alunos, Controller, Service, Repository e JPA. |
| [Sprint 3](Sprint%203/) | DTOs e operações CRUD completas com tratamento explícito de Optional. |
| [Sprint 4](Sprint%204/) | Revisão de JPA, DTO e Lombok; atualização e exclusão. |
| [Sprint 5](Sprint%205/) | Validação, tratamento global de exceções e erros padronizados. |
| [Sprint 6](Sprint%206/) | Paginação, filtros, ordenação, faixa de preços e links HATEOAS. |
| [Sprint 7](Sprint%207/) | BCrypt, JWT, RBAC e documentação Swagger/OpenAPI. |

## Tecnologias
Java 21, Spring Boot 3.4.5, Spring Web, Spring Data JPA, Spring Security, Maven, Lombok, Jakarta Validation, H2, JJWT e Springdoc OpenAPI.

## Como executar
Cada sprint é um projeto Maven independente. Entre na pasta desejada e execute:
```bash
mvn spring-boot:run
```
As aplicações usam H2 em memória. Execute uma por vez para evitar conflito na porta 8080. A Sprint 7 exige configurar JWT_SECRET.

## Observações
- As Sprints 1 a 5 foram preservadas.
- Sprints 6 e 7 são projetos independentes, com pom.xml e configurações próprias.
- Consulte o README de cada sprint para instruções de execução e teste.
