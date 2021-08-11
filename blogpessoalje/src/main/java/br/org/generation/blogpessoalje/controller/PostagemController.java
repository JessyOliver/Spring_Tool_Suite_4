package br.org.generation.blogpessoalje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoalje.model.Postagem;
import br.org.generation.blogpessoalje.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")//@RequestMapping(value="/postagens" , method = RequestMethod.GET)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	
	
	//lista postagem por id - método 1 - if/else	
	/*
	@GetMapping("idifelse/{id}")
	public ResponseEntity<Postagem> getByIdIfElse(@PathVariable long id){
		
		//pode ter dados ou não por isso se usa optionaç para evitar erros na aplicação
		Optional<Postagem> postagem = postagemRepository.findById(id);//a consulta é feita através do id da consulta
		if (postagem.isPresent()) {
			
			return ResponseEntity.ok(postagem.get());// caso encontrre ele mostra o conteudo
			
		}
		return ResponseEntity.notFound().build();// caso não encontre nada ele retorna que não encontrou nada no id
		
	}//fim @GetMapping("idifelse/{id}")
	

	//lista postagem por id - método 2- Try/catch	
	@GetMapping("/idtrycatch/{id}")
	public ResponseEntity<Postagem> getByIdTryCatch(@PathVariable long id){
		
		Optional<Postagem> postagem = postagemRepository.findById(id);//a consulta é feita através do id da consulta
		
		try {
			
			return ResponseEntity.ok(postagem.get());// caso encontrre ele mostra o conteudo

		} catch (Exception e) {
			
			return ResponseEntity.notFound().build();// caso não encontre nada ele retorna que não encontrou nada no id

		}
		
	}
	*/

	
	
	//metodo de conexão com a class PostagemRepository
		@Autowired
		private PostagemRepository postagemRepository;
		
				
		
		//buscando e exibindo os valores que foram salvos no banco
		@GetMapping
		public ResponseEntity<List<Postagem>> getAll (){
			
			return ResponseEntity.ok(postagemRepository.findAll()); 
			
		}
		
	//lista postagem por id - método 3- lambda
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){
		
		return postagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))// se der tudo certo
				.orElse(ResponseEntity.notFound().build());//caso venha algo diferente do esperado
		
	}
	
	//listar todas as postagens por titulo
	//a lista será criada mesmo que não exista nad, pois a tabela sera exibida da mesma forma
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));//busca da class PostagemRepository
		
	}
	
	//criar postagem
	//convert Json em postagem
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@RequestBody Postagem postagem){
		//confirmando que o dado foi salvo no banco
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
		
	}
	
	//alterar postagem
	//se não mandar um id ele cria um novo id 
	@PutMapping
	public ResponseEntity<Postagem> putPostagem (@RequestBody Postagem postagem){
		
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}
	
	//deletar postagem
	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {
		
		postagemRepository.deleteById(id);
		
	}
	
	
	
	
	
	
	//fim tudo
}
