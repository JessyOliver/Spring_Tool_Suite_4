package br.com.minhalojadegame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minhalojadegame.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	//select * from tb_categoria where descricao like "%palavrachave%"
	public List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
