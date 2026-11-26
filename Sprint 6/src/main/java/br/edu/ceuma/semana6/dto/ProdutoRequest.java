package br.edu.ceuma.semana6.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String categoria;
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal preco;
    private boolean ativo = true;
}