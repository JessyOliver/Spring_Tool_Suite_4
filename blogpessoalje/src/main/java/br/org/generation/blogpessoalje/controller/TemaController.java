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
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoalje.model.Tema;
import br.org.generation.blogpessoalje.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {
	
	//metodo de conecxão com a class TemaRepository
	@Autowired
	private TemaRepository temaRepository;
	
	//buscando e exibindo os valores salvos no BD
	@GetMapping
	public ResponseEntity<List<Tema>> getAll(){
		
			return ResponseEntity.ok(temaRepository.findAll());
	}
	
	//lista de postagem por id com o lambda
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id){
		
		return temaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
				
	}
	
	//listar descrição
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){
				
			return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	//criando postagem e salvando no banco
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(tema));
		
	}
	
	//realilzando uma alteração no campo tema no banco
	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema){
			
			return ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(tema));
	}
	
	//deletando um tema da tabela
	@DeleteMapping("/{id}")
	public void deleteTema(@PathVariable long id) {
		temaRepository.deleteById(id);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
