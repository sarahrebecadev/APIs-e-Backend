package br.edu.ceuma.semana6.repositorio;

import br.edu.ceuma.semana6.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {}