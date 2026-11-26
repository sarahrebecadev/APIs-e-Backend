# Sprint 7 — Autenticação, JWT, RBAC e Swagger

## Objetivo
Proteger a API com registro e login, hash de senhas com BCrypt, tokens JWT, autorização por perfil e documentação OpenAPI.

## Endpoints
| Método | Rota | Acesso | Resultado |
|---|---|---|---|
| POST | `/register` | Público | 201 — cria usuário comum |
| POST | `/login` | Público | 200 — retorna JWT |
| GET | `/usuarios/perfil` | Autenticado | 200 — dados do usuário logado |
| DELETE | `/usuarios/{id}` | ADMIN | 204 — exclui usuário |
| GET | `/api-docs` | Público | Swagger UI |

OpenAPI JSON: `/v3/api-docs`.

## Segurança
- BCrypt armazena somente hash da senha.
- DTOs nunca retornam senha ou hash.
- JWT assinado com segredo externo e expiração de uma hora.
- Payload contém ID, perfil e e-mail (subject).
- Filtro valida assinatura e expiração.
- RBAC exige ADMIN para DELETE; perfil comum recebe 403.
- Registro público sempre cria perfil COMUM, sem aceitar promoção pelo cliente.

## Configuração
Requisitos: JDK 21 e Maven. Configure variáveis de ambiente antes de iniciar.

Linux/macOS:
```bash
export JWT_SECRET="$(openssl rand -base64 32)"
export APP_ADMIN_EMAIL="admin@exemplo.com"
export APP_ADMIN_PASSWORD="DefinaUmaSenhaForteAqui"
mvn spring-boot:run
```

PowerShell:
```powershell
$env:JWT_SECRET = [Convert]::ToBase64String((1..32 | ForEach-Object { Get-Random -Maximum 256 }))
$env:APP_ADMIN_EMAIL = "admin@exemplo.com"
$env:APP_ADMIN_PASSWORD = "DefinaUmaSenhaForteAqui"
mvn spring-boot:run
```

O administrador inicial é criado somente quando as variáveis APP_ADMIN estão definidas. Sua senha também é armazenada com BCrypt. Sem essas variáveis, usuários cadastrados são comuns.

`.env.example` documenta os nomes das variáveis, mas Spring Boot não carrega esse arquivo automaticamente. Configure as variáveis no terminal ou IDE. Nunca publique segredo ou arquivo .env real.

## Testar
1. Abra `http://localhost:8080/api-docs`.
2. POST /register:
```json
{"nome":"Ana Silva","email":"ana@email.com","senha":"SenhaSegura123"}
```
3. POST /login e copie o campo token.
4. Clique em Authorize no Swagger e informe o JWT.
5. Teste GET /usuarios/perfil.
6. Tente DELETE /usuarios/{id} com usuário comum: resposta esperada 403. Faça login como administrador para testar a exclusão autorizada.

Header das rotas protegidas:
```http
Authorization: Bearer SEU_TOKEN
```

## Reflexão

**Por que não armazenar senhas em texto puro ou criptografia reversível?** Se o banco for exposto, senhas em texto puro entregam imediatamente as credenciais. Hashes de senha como BCrypt dificultam a recuperação da senha original, usando salt e custo computacional. A aplicação compara a senha informada com o hash armazenado, sem recuperar a senha original.

**Por que Swagger/OpenAPI é mais que formalidade?** A documentação define o contrato entre backend e frontend: rotas, parâmetros, formatos de entrada e respostas. Isso reduz erros de integração, acelera testes e permite verificar rotas protegidas com Bearer JWT.

## Observações
- H2 em memória: usuários são apagados ao encerrar a aplicação.
- Em produção, HTTPS, banco persistente e gestão segura de segredos são obrigatórios.
- Projeto didático; produção exige também rate limiting, recuperação segura de conta e auditoria.
