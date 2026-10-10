package br.edu.ceuma.semana3.excecao;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(Long id) {
        super("Aluno não encontrado com o ID: " + id);
    }
}
