package br.edu.ceuma.semana3.dto;

public class AlunoRequest {
    private String nome;
    private int idade;

    public AlunoRequest() {}
    public AlunoRequest(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
}