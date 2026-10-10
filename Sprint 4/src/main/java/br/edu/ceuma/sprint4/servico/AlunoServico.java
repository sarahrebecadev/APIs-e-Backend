package br.edu.ceuma.sprint4.servico;

import br.edu.ceuma.sprint4.dto.AlunoRequest;
import br.edu.ceuma.sprint4.dto.AlunoResponse;
import br.edu.ceuma.sprint4.entidade.Aluno;
import br.edu.ceuma.sprint4.repositorio.AlunoRepositorio;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServico {
    private final AlunoRepositorio repositorio;
    public AlunoServico(AlunoRepositorio repositorio) { this.repositorio = repositorio; }
    public AlunoResponse criar(AlunoRequest request) {
        Aluno aluno = new Aluno(); aluno.setNome(request.getNome()); aluno.setIdade(request.getIdade());
        return converter(repositorio.save(aluno));
    }
    public List<AlunoResponse> listar() {
        List<AlunoResponse> respostas = new ArrayList<>();
        for (Aluno aluno : repositorio.findAll()) respostas.add(converter(aluno));
        return respostas;
    }
    public Optional<AlunoResponse> buscarPorId(Long id) {
        Optional<Aluno> aluno = repositorio.findById(id);
        if (aluno.isPresent()) return Optional.of(converter(aluno.get()));
        else return Optional.empty();
    }
    public Optional<AlunoResponse> atualizar(Long id, AlunoRequest request) {
        Optional<Aluno> encontrado = repositorio.findById(id);
        if (encontrado.isPresent()) {
            Aluno aluno = encontrado.get(); aluno.setNome(request.getNome()); aluno.setIdade(request.getIdade());
            return Optional.of(converter(repositorio.save(aluno)));
        }
        return Optional.empty();
    }
    public boolean excluir(Long id) {
        if (!repositorio.existsById(id)) return false;
        repositorio.deleteById(id);
        return true;
    }
    private AlunoResponse converter(Aluno aluno) { return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade()); }
}