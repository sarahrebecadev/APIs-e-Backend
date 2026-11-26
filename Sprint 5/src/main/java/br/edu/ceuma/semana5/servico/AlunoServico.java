package br.edu.ceuma.semana5.servico;

import br.edu.ceuma.semana5.dto.AlunoRequest;
import br.edu.ceuma.semana5.dto.AlunoResponse;
import br.edu.ceuma.semana5.entidade.Aluno;
import br.edu.ceuma.semana5.excecao.AlunoJaExisteException;
import br.edu.ceuma.semana5.excecao.RecursoNaoEncontradoException;
import br.edu.ceuma.semana5.repositorio.AlunoRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AlunoServico {
    private final AlunoRepositorio repositorio;
    public AlunoServico(AlunoRepositorio repositorio) { this.repositorio = repositorio; }

    public AlunoResponse criar(AlunoRequest request) {
        if (repositorio.existsByEmail(request.getEmail())) {
            throw new AlunoJaExisteException("Já existe um aluno cadastrado com este e-mail.");
        }
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());
        aluno.setIdade(request.getIdade());
        return converter(repositorio.save(aluno));
    }

    public AlunoResponse buscarPorId(Long id) {
        Aluno aluno = repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado."));
        return converter(aluno);
    }

    public void atualizar(Long id, AlunoRequest request) {
        Aluno aluno = repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado."));
        if (repositorio.existsByEmail(request.getEmail()) && !aluno.getEmail().equals(request.getEmail())) {
            throw new AlunoJaExisteException("Já existe outro aluno cadastrado com este e-mail.");
        }
        aluno.setNome(request.getNome());
        aluno.setEmail(request.getEmail());
        aluno.setIdade(request.getIdade());
        repositorio.save(aluno);
    }

    public void remover(Long id) {
        boolean existe = repositorio.existsById(id);
        if (!existe) {
            throw new RecursoNaoEncontradoException("Aluno não encontrado.");
        }
        repositorio.deleteById(id);
    }

    private AlunoResponse converter(Aluno aluno) {
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getIdade());
    }
}