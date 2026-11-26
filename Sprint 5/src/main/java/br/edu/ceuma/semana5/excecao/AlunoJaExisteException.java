package br.edu.ceuma.semana5.excecao;

public class AlunoJaExisteException extends RuntimeException {
    public AlunoJaExisteException(String mensagem) { super(mensagem); }
}