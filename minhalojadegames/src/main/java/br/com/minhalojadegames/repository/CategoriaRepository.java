package br.com.minhalojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minhalojadegames.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	//select * from tb_categoria where descricao like "%palavrachave%"
	public List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
