package br.edu.ceuma.sprint4.repositorio;

import br.edu.ceuma.sprint4.entidade.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {}