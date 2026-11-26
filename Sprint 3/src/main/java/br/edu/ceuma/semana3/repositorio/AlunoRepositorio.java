package br.edu.ceuma.semana3.repositorio;

import br.edu.ceuma.semana3.entidade.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {}