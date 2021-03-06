package br.com.minhalojadegame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minhalojadegame.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//select *from tb_produto_game where titulo like "%palavra chave%"
	public List<Produto> findAllByTituloContainingIgnoreCase(String titulo);
	

}
