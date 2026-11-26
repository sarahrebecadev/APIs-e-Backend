package br.edu.ceuma.semana4.servico;

import br.edu.ceuma.semana4.dto.AlunoRequest;
import br.edu.ceuma.semana4.dto.AlunoResponse;
import br.edu.ceuma.semana4.entidade.Aluno;
import br.edu.ceuma.semana4.repositorio.AlunoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AlunoServico {
    private final AlunoRepositorio repositorio;

    public AlunoServico(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public AlunoResponse create(AlunoRequest request) {
        Aluno aluno = new Aluno(null, request.getNome(), request.getIdade());
        Aluno salvo = repositorio.save(aluno);
        return converterParaResponse(salvo);
    }

    public AlunoResponse getById(Long id) {
        Aluno aluno = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID: " + id));
        return converterParaResponse(aluno);
    }

    public void update(Long id, AlunoRequest request) {
        Aluno aluno = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com o ID: " + id));
        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());
        repositorio.save(aluno);
    }

    public void deleteById(Long id) {
        boolean existe = repositorio.existsById(id);
        if (!existe) {
            throw new IllegalArgumentException("Aluno não encontrado com o ID: " + id);
        }
        repositorio.deleteById(id);
    }

    private AlunoResponse converterParaResponse(Aluno aluno) {
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade());
    }
}