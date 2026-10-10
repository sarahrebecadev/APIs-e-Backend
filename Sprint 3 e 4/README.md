# API de Alunos — Sprints 3 e 4

Projeto Spring Boot com Java 21, Spring Data JPA, PostgreSQL, DTOs e Lombok.

## Executar

1. Configure PostgreSQL e as variáveis de ambiente opcionais:
   - `BANCO_URL` (padrão: `jdbc:postgresql://localhost:5432/ceuma`)
   - `BANCO_USUARIO` (padrão: `postgres`)
   - `BANCO_SENHA` (senha do banco)
2. Entre na pasta `Sprint 3 e 4`.
3. Execute:

```bash
mvn spring-boot:run
```

A API fica disponível em `http://localhost:8080`.

## Endpoints

| Método | Caminho | Sucesso |
|---|---|---|
| GET | `/alunos` | 200 OK |
| GET | `/alunos/{id}` | 200 OK |
| POST | `/alunos` | 201 Created |
| PUT | `/alunos/{id}` | 204 No Content |
| DELETE | `/alunos/{id}` | 204 No Content |

IDs inexistentes em GET por ID, PUT e DELETE retornam 404 Not Found.

## Exemplo de JSON para POST e PUT

```json
{
  "nome": "Maria Silva",
  "idade": 21
}
```

## Observações

- Os Controllers recebem e retornam DTOs, não a Entity.
- O DELETE verifica a existência com `existsById` e um `if (!exists)`, sem lambda.
- O PUT utiliza `findById(id).orElseThrow(...)`.
- A compilação e os testes devem ser executados no ambiente local após configurar o banco.
