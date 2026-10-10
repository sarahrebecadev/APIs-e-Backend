package br.edu.ceuma.sprint4.dto;

public class AlunoRequest {
    private String nome;
    private int idade;
    public AlunoRequest() {}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
}