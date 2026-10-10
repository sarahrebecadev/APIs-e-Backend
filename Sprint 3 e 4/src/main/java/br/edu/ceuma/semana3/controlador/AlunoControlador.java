package br.edu.ceuma.semana3.controlador;

import br.edu.ceuma.semana3.dto.AlunoRequest;
import br.edu.ceuma.semana3.dto.AlunoResponse;
import br.edu.ceuma.semana3.servico.AlunoServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoControlador {

    private final AlunoServico alunoServico;

    public AlunoControlador(AlunoServico alunoServico) {
        this.alunoServico = alunoServico;
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listar() {
        return ResponseEntity.ok(alunoServico.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alunoServico.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody AlunoRequest request) {
        AlunoResponse resposta = alunoServico.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody AlunoRequest request) {
        alunoServico.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        alunoServico.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
