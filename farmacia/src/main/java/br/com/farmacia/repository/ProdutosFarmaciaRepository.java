package br.com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.farmacia.model.ProdutoFarmacia;

@Repository
public interface ProdutosFarmaciaRepository extends JpaRepository<ProdutoFarmacia, Long>{

	public List<ProdutoFarmacia> findAllByNomeContainingIgnoreCase(String nome);
}
