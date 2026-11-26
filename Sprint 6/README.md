# Sprint 6 — Paginação, filtros e ordenação

## Objetivo
Disponibilizar consultas eficientes de produtos usando paginação no banco, filtros por query parameters, ordenação e metadados de navegação.

## Endpoints
- `GET /produtos`: lista produtos com paginação, filtros e ordenação.
- `POST /produtos`: cadastra produtos para facilitar os testes.

## Parâmetros do GET
| Parâmetro | Exemplo | Descrição |
|---|---|---|
| `page` | `1` | Página (começa em 1) |
| `limit` | `10` | Itens por página; máximo 100 |
| `sort` | `preco` | Campo: id, nome, categoria, preco ou ativo |
| `order` | `desc` | Direção asc ou desc |
| `categoria` | `eletronicos` | Filtro exato, sem diferenciar maiúsculas |
| `ativo` | `true` | Filtra produtos ativos/inativos |
| `minPrice` / `maxPrice` | `100` / `500` | Faixa de preço inclusiva |
| `search` | `mouse` | Busca parcial no nome |

Exemplo:
```http
GET http://localhost:8080/produtos?page=1&limit=5&sort=preco&order=desc&categoria=eletronicos&ativo=true&minPrice=50&maxPrice=500
```

## Formato da resposta
```json
{
  "data": [
    {"id": 1, "nome": "Teclado Mecânico", "categoria": "eletronicos", "preco": 249.90, "ativo": true}
  ],
  "meta": {
    "totalItems": 8,
    "itemsPerPage": 5,
    "currentPage": 1,
    "totalPages": 2,
    "first": "/produtos?limit=5&page=1",
    "prev": null,
    "next": "/produtos?limit=5&page=2",
    "last": "/produtos?limit=5&page=2"
  }
}
```
Os links preservam os filtros e a ordenação informados. O limite é validado e campos de ordenação são permitidos por lista, evitando ordenar por propriedades arbitrárias.

## Executar
Requisitos: JDK 21 e Maven.
```bash
mvn spring-boot:run
```
Banco H2 em memória; alguns produtos de exemplo são inseridos automaticamente. Dados são reiniciados ao encerrar a aplicação.

## Reflexão
**Por que não retornar todos os registros de uma vez?** Em bases grandes, uma consulta sem limite pode consumir memória excessiva, aumentar o tempo de resposta e congestionar a rede. Em situações extremas, pode causar falta de memória (OOM) e indisponibilidade do serviço. A paginação busca somente uma parte dos registros no banco.

**Como os metadados ajudam o frontend?** Um objeto `meta` padronizado informa total de itens, tamanho da página, página atual e total de páginas. Com isso, o frontend consegue criar componentes reutilizáveis de navegação sem deduzir os dados ou montar links manualmente. Os links HATEOAS tornam a navegação ainda mais direta.
