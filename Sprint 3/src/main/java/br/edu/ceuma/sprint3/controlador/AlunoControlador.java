package br.edu.ceuma.sprint3.controlador;

import br.edu.ceuma.sprint3.dto.AlunoRequest;
import br.edu.ceuma.sprint3.dto.AlunoResponse;
import br.edu.ceuma.sprint3.servico.AlunoServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoControlador {
    private final AlunoServico servico;
    public AlunoControlador(AlunoServico servico) { this.servico = servico; }
    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody AlunoRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(request)); }
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listar() { return ResponseEntity.ok(servico.listar()); }
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@PathVariable Long id) {
        Optional<AlunoResponse> aluno = servico.buscarPorId(id);
        if (aluno.isPresent()) return ResponseEntity.ok(aluno.get());
        else return ResponseEntity.notFound().build();
    }
}