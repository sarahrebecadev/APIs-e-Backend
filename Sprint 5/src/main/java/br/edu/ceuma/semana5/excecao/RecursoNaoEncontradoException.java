package br.edu.ceuma.semana5.excecao;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) { super(mensagem); }
}