package br.edu.ceuma.semana3.servico;

import br.edu.ceuma.semana3.dto.AlunoRequest;
import br.edu.ceuma.semana3.dto.AlunoResponse;
import br.edu.ceuma.semana3.entidade.Aluno;
import br.edu.ceuma.semana3.repositorio.AlunoRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoServico {
    private final AlunoRepositorio repositorio;

    public AlunoServico(AlunoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public AlunoResponse create(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());
        Aluno salvo = repositorio.save(aluno);
        return converterParaResponse(salvo);
    }

    public AlunoResponse getById(Long id) {
        Optional<Aluno> optionalAluno = repositorio.findById(id);
        if (optionalAluno.isPresent()) {
            return converterParaResponse(optionalAluno.get());
        } else {
            throw new IllegalArgumentException("Aluno não encontrado com o ID: " + id);
        }
    }

    public void update(Long id, AlunoRequest request) {
        Optional<Aluno> optionalAluno = repositorio.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setNome(request.getNome());
            aluno.setIdade(request.getIdade());
            repositorio.save(aluno);
        } else {
            throw new IllegalArgumentException("Aluno não encontrado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        Optional<Aluno> optionalAluno = repositorio.findById(id);
        if (optionalAluno.isPresent()) {
            repositorio.delete(optionalAluno.get());
        } else {
            throw new IllegalArgumentException("Aluno não encontrado com o ID: " + id);
        }
    }

    private AlunoResponse converterParaResponse(Aluno aluno) {
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade());
    }
}