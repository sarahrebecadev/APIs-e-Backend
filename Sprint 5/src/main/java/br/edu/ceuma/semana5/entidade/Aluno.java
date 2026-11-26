package br.edu.ceuma.semana5.entidade;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "alunos", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    private int idade;
}