package br.edu.ceuma.semana6.dto;

import br.edu.ceuma.semana6.entidade.Produto;
import java.math.BigDecimal;

public record ProdutoResponse(Long id, String nome, String categoria, BigDecimal preco, boolean ativo) {
    public static ProdutoResponse de(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getCategoria(),
                produto.getPreco(), produto.isAtivo());
    }
}