package br.edu.ceuma.semana3.controlador;

import br.edu.ceuma.semana3.dto.AlunoRequest;
import br.edu.ceuma.semana3.dto.AlunoResponse;
import br.edu.ceuma.semana3.servico.AlunoServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoControlador {
    private final AlunoServico servico;

    public AlunoControlador(AlunoServico servico) {
        this.servico = servico;
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> create(@RequestBody AlunoRequest request) {
        AlunoResponse resposta = servico.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(servico.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AlunoRequest request) {
        servico.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.noContent().build();
    }
}