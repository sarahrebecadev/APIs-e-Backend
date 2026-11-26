package br.edu.ceuma.semana6.controlador;

import br.edu.ceuma.semana6.dto.*;
import br.edu.ceuma.semana6.servico.ProdutoServico;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/produtos")
public class ProdutoControlador {
    private final ProdutoServico servico;
    public ProdutoControlador(ProdutoServico servico) { this.servico = servico; }

    @GetMapping
    public ResponseEntity<PaginaResponse<ProdutoResponse>> listar(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean ativo,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String search) {
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("minPrice não pode ser maior que maxPrice.");
        }
        return ResponseEntity.ok(servico.listar(page, limit, sort, order, categoria, ativo, minPrice, maxPrice, search));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(request));
    }
}