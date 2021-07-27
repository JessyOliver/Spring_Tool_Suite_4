package br.org.generation.blogpessoalje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoalje.model.Postagem;

@Repository
public interface PostagemRepository  extends JpaRepository<Postagem, Long> {

	//selecionando e buscando o campo titulo na tabela de Postagem
	public List <Postagem> findAllByTituloContainingIgnoreCase(String titulo);
	
}
