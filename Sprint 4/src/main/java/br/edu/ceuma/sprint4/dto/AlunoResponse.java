package br.edu.ceuma.sprint4.dto;

public class AlunoResponse {
    private Long id;
    private String nome;
    private int idade;
    public AlunoResponse() {}
    public AlunoResponse(Long id, String nome, int idade) { this.id = id; this.nome = nome; this.idade = idade; }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
}