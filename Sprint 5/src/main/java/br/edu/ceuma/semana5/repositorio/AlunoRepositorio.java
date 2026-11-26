package br.edu.ceuma.semana5.repositorio;

import br.edu.ceuma.semana5.entidade.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {
    boolean existsByEmail(String email);
}