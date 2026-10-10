package br.edu.ceuma.semana3.dto;

import br.edu.ceuma.semana3.entidade.Aluno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponse {

    private Long id;
    private String nome;
    private int idade;

    public static AlunoResponse fromEntity(Aluno aluno) {
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade());
    }
}
