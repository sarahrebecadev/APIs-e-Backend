# APIs e Backend — TechX

Repositório de atividades práticas de desenvolvimento de APIs REST e backend com Java e Spring Boot.

## Organização

| Etapa | Conteúdo |
|---|---|
| [Sprint 1](Sprint%201/) | Primeiros passos com Spring Boot, JPA e entidade Curso. |
| [Sprint 2](Sprint%202/) | API inicial de alunos, com Controller, Service, Repository e JPA. |
| [Sprint 3](Sprint%203/) | DTOs de entrada e saída e operações CRUD completas com tratamento explícito de Optional. |
| [Sprint 4](Sprint%204/) | Revisão de JPA, DTO e Lombok; atualização e exclusão com verificação de existência. |
| [Sprint 5](Sprint%205/) | Validação de dados, tratamento global de exceções, erros padronizados e exceções customizadas. |

## Tecnologias
Java 21, Spring Boot 3.4.5, Spring Web, Spring Data JPA, Maven, Lombok, Jakarta Bean Validation e H2.

## Como executar
As Sprints 3, 4 e 5 são projetos Maven independentes. Abra um terminal na pasta da sprint desejada e execute:

```bash
mvn spring-boot:run
```

As aplicações usam H2 em memória e iniciam, por padrão, na porta 8080. Execute uma por vez para evitar conflito de porta.

## Observações
- As pastas das Sprints 1 e 2 foram preservadas.
- As Sprints 3, 4 e 5 são projetos independentes e progressivos do domínio de alunos.
- H2 foi escolhido nas novas etapas para permitir testes locais sem configurar um serviço externo.
