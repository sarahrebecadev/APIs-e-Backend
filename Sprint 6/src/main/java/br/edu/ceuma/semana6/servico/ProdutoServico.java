package br.edu.ceuma.semana6.servico;

import br.edu.ceuma.semana6.dto.PaginaResponse;
import br.edu.ceuma.semana6.dto.ProdutoRequest;
import br.edu.ceuma.semana6.dto.ProdutoResponse;
import br.edu.ceuma.semana6.entidade.Produto;
import br.edu.ceuma.semana6.repositorio.ProdutoRepositorio;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoServico {
    private static final Set<String> CAMPOS_ORDENACAO = Set.of("id", "nome", "categoria", "preco", "ativo");
    private final ProdutoRepositorio repositorio;

    public ProdutoServico(ProdutoRepositorio repositorio) { this.repositorio = repositorio; }

    @Transactional(readOnly = true)
    public PaginaResponse<ProdutoResponse> listar(int page, int limit, String sort, String order,
            String categoria, Boolean ativo, BigDecimal minPrice, BigDecimal maxPrice, String search) {
        int pagina = Math.max(page, 1);
        int tamanho = Math.min(Math.max(limit, 1), 100);
        String campo = CAMPOS_ORDENACAO.contains(sort) ? sort : "id";
        Sort.Direction direcao = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pagina - 1, tamanho, Sort.by(direcao, campo));

        Specification<Produto> filtro = Specification.where(null);
        if (categoria != null && !categoria.isBlank()) {
            filtro = filtro.and((root, query, cb) -> cb.equal(cb.lower(root.get("categoria")), categoria.toLowerCase()));
        }
        if (ativo != null) {
            filtro = filtro.and((root, query, cb) -> cb.equal(root.get("ativo"), ativo));
        }
        if (minPrice != null) {
            filtro = filtro.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("preco"), minPrice));
        }
        if (maxPrice != null) {
            filtro = filtro.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("preco"), maxPrice));
        }
        if (search != null && !search.isBlank()) {
            filtro = filtro.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + search.toLowerCase() + "%"));
        }

        Page<Produto> resultado = repositorio.findAll(filtro, pageable);
        List<ProdutoResponse> dados = resultado.getContent().stream().map(ProdutoResponse::de).toList();
        String base = "/produtos?limit=" + tamanho + "&sort=" + campo + "&order=" + direcao.name().toLowerCase()
                + parametro("categoria", categoria) + parametro("ativo", ativo)
                + parametro("minPrice", minPrice) + parametro("maxPrice", maxPrice) + parametro("search", search);
        int total = resultado.getTotalPages();
        String first = base + "&page=1";
        String last = base + "&page=" + Math.max(total, 1);
        String prev = pagina > 1 && total > 0 ? base + "&page=" + (pagina - 1) : null;
        String next = pagina < total ? base + "&page=" + (pagina + 1) : null;
        PaginaResponse.Meta meta = new PaginaResponse.Meta(resultado.getTotalElements(), tamanho,
                pagina, total, first, prev, next, last);
        return new PaginaResponse<>(dados, meta);
    }

    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setCategoria(request.getCategoria());
        produto.setPreco(request.getPreco());
        produto.setAtivo(request.isAtivo());
        return ProdutoResponse.de(repositorio.save(produto));
    }

    private String parametro(String nome, Object valor) {
        if (valor == null || valor.toString().isBlank()) return "";
        return "&" + nome + "=" + URLEncoder.encode(valor.toString(), StandardCharsets.UTF_8);
    }
}