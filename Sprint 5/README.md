# Sprint 5 — Validação, tratamento global e erros padronizados

## Objetivo
Validar os dados recebidos, centralizar o tratamento de falhas e devolver respostas consistentes sem expor detalhes internos.

## Validação
A classe `AlunoRequest` utiliza Jakarta Bean Validation:
- `@NotBlank`: nome e e-mail obrigatórios;
- `@Email`: valida o formato do e-mail;
- `@Min(1)`: idade maior que zero;
- `@Valid` no Controller aplica as regras antes de executar o Service.

A validação ocorre no POST e PUT.

## Formato padronizado de erro
```json
{
  "status": 400,
  "error": "VALIDATION_ERROR",
  "message": "Os dados fornecidos são inválidos.",
  "details": [
    {"field": "email", "message": "O formato do e-mail está incorreto."}
  ],
  "timestamp": "2026-01-01T10:00:00Z"
}
```

## Tratamentos
- Dados inválidos e JSON malformado: HTTP 400;
- Rota ou aluno não encontrado: HTTP 404;
- E-mail já cadastrado e conflito de integridade: HTTP 409;
- Falhas inesperadas: HTTP 500, registradas internamente via SLF4J.

O stack trace permanece nos logs do servidor e não é enviado ao cliente.

## Exceções customizadas
- `AlunoJaExisteException`: e-mail duplicado (409 Conflict).
- `RecursoNaoEncontradoException`: aluno inexistente (404 Not Found).

## Endpoints
| Método | Rota | Sucesso |
|---|---|---|
| POST | `/alunos` | 201 Created |
| GET | `/alunos/{id}` | 200 OK |
| PUT | `/alunos/{id}` | 204 No Content |
| DELETE | `/alunos/{id}` | 204 No Content |

## Executar
Requisitos: JDK 21 e Maven. Dentro desta pasta:
```bash
mvn spring-boot:run
```
A aplicação inicia em `http://localhost:8080`, usando H2 em memória.

## Teste de cadastro
```http
POST http://localhost:8080/alunos
Content-Type: application/json

{"nome":"Maria Silva","email":"maria@email.com","idade":22}
```

Para testar validação, envie e-mail inválido, nome vazio ou idade zero. Para testar conflito, repita um e-mail já cadastrado. Para testar 404, acesse uma rota inexistente ou um ID que não existe.

## Reflexão

### Por que expor o stack trace é uma falha de segurança?
O stack trace pode revelar nomes de classes, estrutura de pastas, bibliotecas e detalhes da implementação. Essas informações ajudam terceiros a entender a arquitetura interna e podem facilitar a identificação de vulnerabilidades. Por isso, detalhes técnicos devem permanecer nos logs internos, enquanto o cliente recebe uma mensagem genérica.

### Como um padrão de erros ajuda o frontend?
Um formato previsível permite que o frontend leia sempre os mesmos campos, identifique o tipo de falha, apresente mensagens adequadas e destaque campos inválidos. Assim, o tratamento pode ser automatizado sem depender de interpretações diferentes para cada endpoint.

## Observação
O banco H2 é temporário: os dados são reiniciados quando a aplicação é encerrada.
