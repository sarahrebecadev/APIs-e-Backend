package br.edu.ceuma.semana7.excecao;
public class EmailJaCadastradoException extends RuntimeException {
 public EmailJaCadastradoException(){super("Já existe uma conta cadastrada com este e-mail.");}
}