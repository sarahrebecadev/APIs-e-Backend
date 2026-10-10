package br.edu.ceuma.semana3.servico;

import br.edu.ceuma.semana3.dto.AlunoRequest;
import br.edu.ceuma.semana3.dto.AlunoResponse;
import br.edu.ceuma.semana3.entidade.Aluno;
import br.edu.ceuma.semana3.excecao.AlunoNaoEncontradoException;
import br.edu.ceuma.semana3.repositorio.AlunoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServico {

    private final AlunoRepositorio alunoRepositorio;

    public AlunoServico(AlunoRepositorio alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    public List<AlunoResponse> listarTodos() {
        List<Aluno> alunos = alunoRepositorio.findAll();
        List<AlunoResponse> respostas = new ArrayList<>();

        for (Aluno aluno : alunos) {
            respostas.add(AlunoResponse.fromEntity(aluno));
        }

        return respostas;
    }

    public AlunoResponse buscarPorId(Long id) {
        Optional<Aluno> optional = alunoRepositorio.findById(id);

        if (optional.isPresent()) {
            return AlunoResponse.fromEntity(optional.get());
        } else {
            throw new AlunoNaoEncontradoException(id);
        }
    }

    public AlunoResponse criar(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());

        Aluno salvo = alunoRepositorio.save(aluno);
        return AlunoResponse.fromEntity(salvo);
    }

    public void update(Long id, AlunoRequest request) {
        Aluno aluno = alunoRepositorio.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));

        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());

        alunoRepositorio.save(aluno);
    }

    public void deleteById(Long id) {
        boolean exists = alunoRepositorio.existsById(id);

        if (!exists) {
            throw new AlunoNaoEncontradoException(id);
        }

        alunoRepositorio.deleteById(id);
    }
}
