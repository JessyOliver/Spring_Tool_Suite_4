package br.com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.farmacia.model.CategoriaFarmacia;

@Repository
public interface CategoriaFarmaciaRepository extends JpaRepository<CategoriaFarmacia, Long>{

	
	public List<CategoriaFarmacia> findAllByDescricaoContainingIgnoreCase(String descricao);
	
}
