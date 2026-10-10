package br.edu.ceuma.sprint3.servico;

import br.edu.ceuma.sprint3.dto.AlunoRequest;
import br.edu.ceuma.sprint3.dto.AlunoResponse;
import br.edu.ceuma.sprint3.entidade.Aluno;
import br.edu.ceuma.sprint3.repositorio.AlunoRepositorio;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServico {
    private final AlunoRepositorio repositorio;
    public AlunoServico(AlunoRepositorio repositorio) { this.repositorio = repositorio; }
    public AlunoResponse criar(AlunoRequest request) { return converter(repositorio.save(new Aluno(request.getNome(), request.getIdade()))); }
    public List<AlunoResponse> listar() {
        List<AlunoResponse> respostas = new ArrayList<>();
        for (Aluno aluno : repositorio.findAll()) respostas.add(converter(aluno));
        return respostas;
    }
    public Optional<AlunoResponse> buscarPorId(Long id) {
        Optional<Aluno> encontrado = repositorio.findById(id);
        if (encontrado.isPresent()) return Optional.of(converter(encontrado.get()));
        else return Optional.empty();
    }
    private AlunoResponse converter(Aluno aluno) { return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade()); }
}