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

import br.org.generation.blogpessoalje.model.Usuario;
import br.org.generation.blogpessoalje.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	//metodo de cnexão com a class Usuariorepository
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//buscando todos os dados da tabela
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	//buscando um usuario pelo id
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable long id){
		return usuarioRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	//buscando usuario pelo nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> getByNome(@PathVariable String nome){
		
		return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
		
	}
	
	//criando uma postagem
	@PostMapping
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
		
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
		
	}
	
	//atualizandoos dados de um usuario na tabela
	@PutMapping
	public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}
	
	
	//deletando o usuario do bd
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable long id) {
		
		usuarioRepository.deleteById(id);
		
	}
	
	
	
	
	
	

}
