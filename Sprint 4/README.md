# Sprint 4 — Revisão geral, PUT, DELETE e Lombok

## Objetivo
Consolidar a API de alunos com JPA, DTOs, Lombok e implementação explícita das operações de atualização e remoção.

## Implementações
- `update`: busca com `findById(id).orElseThrow(...)`, atualiza os campos e persiste com `save()`.
- `deleteById`: verifica `existsById(id)`; usa `if (!existe)` para lançar exceção antes de remover.
- Controller com `@PutMapping("/{id}")` e `@DeleteMapping("/{id}")`.
- PUT e DELETE retornam HTTP 204 (No Content).
- Entity e DTOs utilizam `@Getter`, `@Setter`, `@NoArgsConstructor` e `@AllArgsConstructor` conforme a necessidade de cada classe.
- A separação entre Entity, Request e Response foi mantida.

## Tecnologias
Java 21, Spring Boot 3.4.5, Spring Web, Spring Data JPA, Lombok, Maven e H2.

## Executar
Na pasta Sprint 4:
```bash
mvn spring-boot:run
```
A API inicia em `http://localhost:8080`.

## Endpoints
| Método | Rota | Resultado esperado |
|---|---|---|
| POST | `/alunos` | 201 Created |
| GET | `/alunos/{id}` | 200 OK |
| PUT | `/alunos/{id}` | 204 No Content |
| DELETE | `/alunos/{id}` | 204 No Content |

> O projeto usa H2 em memória para permitir testes locais imediatos. Cada execução reinicia os dados.
