package br.com.farmacia.controller;

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

import br.com.farmacia.model.CategoriaFarmacia;
import br.com.farmacia.repository.CategoriaFarmaciaRepository;

@RestController
@RequestMapping("/farmaciacategorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaFarmaciaController {
	
	@Autowired
	private CategoriaFarmaciaRepository categoriaFarmaciaRepository;
	
	//getAll
		@GetMapping
		public ResponseEntity<List<CategoriaFarmacia>> getAll(){
			
			return ResponseEntity.ok(categoriaFarmaciaRepository.findAll());
			
		}
		
		//getId
		@GetMapping("/{id}")
		public ResponseEntity<CategoriaFarmacia> getById(@PathVariable long id){
			
			return categoriaFarmaciaRepository.findById(id)
					.map(resp -> ResponseEntity.ok(resp))
						.orElse(ResponseEntity.notFound().build());
		}	
		
		
		//getDescricao
		@GetMapping("/descricao/{descricao}") 
		public ResponseEntity<List<CategoriaFarmacia>> getByDescricao(@PathVariable String descricao){
				
			return ResponseEntity.ok(categoriaFarmaciaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
			
		}
		
		//Post cadastrando no DB
		@PostMapping
		public ResponseEntity<CategoriaFarmacia> postCategoria(@RequestBody CategoriaFarmacia categoria){
			
				return ResponseEntity.status(HttpStatus.CREATED).body(categoriaFarmaciaRepository.save(categoria));
		}
		
		//put atualizando dado no DB
		@PutMapping
		public ResponseEntity<CategoriaFarmacia> putCategoria(@RequestBody CategoriaFarmacia categoria){
			
				return ResponseEntity.status(HttpStatus.OK).body(categoriaFarmaciaRepository.save(categoria));
		}
		
		//deletando a categoria da tabela
		@DeleteMapping("/{id}")
		public void deleteCategoria(@PathVariable long id) {
			
			categoriaFarmaciaRepository.deleteById(id);
		}
		
		
		
		
		
		

}
