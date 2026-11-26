package br.edu.ceuma.semana3.dto;

public class AlunoResponse {
    private Long id;
    private String nome;
    private int idade;

    public AlunoResponse() {}
    public AlunoResponse(Long id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
}