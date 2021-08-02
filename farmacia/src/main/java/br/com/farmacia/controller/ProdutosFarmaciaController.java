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

import br.com.farmacia.model.ProdutoFarmacia;
import br.com.farmacia.repository.ProdutosFarmaciaRepository;

@RestController
@RequestMapping("/farmaciaprodutos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosFarmaciaController {
	
	@Autowired
	private ProdutosFarmaciaRepository produtosFarmaciaRepository;
	
	//getAll todos os dados da tabela
		@GetMapping
		public ResponseEntity<List<ProdutoFarmacia>> getAll(){
			
			return ResponseEntity.ok(produtosFarmaciaRepository.findAll());
		}
		
	//getById campo pelo id
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoFarmacia> getById( @PathVariable long id){
		
		return produtosFarmaciaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.notFound().build());
	}
	
	//getByTitulo buscar palavra chave no campo titulo 
		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<ProdutoFarmacia>> getByNome(@PathVariable String nome){
			
			return ResponseEntity.ok(produtosFarmaciaRepository.findAllByNomeContainingIgnoreCase(nome));
			
		} 
		
		//post criando produto
		@PostMapping
		public ResponseEntity<ProdutoFarmacia> postProdutoFarma(@RequestBody ProdutoFarmacia produtofarm){
			
			return ResponseEntity.status(HttpStatus.CREATED).body(produtosFarmaciaRepository.save(produtofarm));
				
		}	
		
		//put atualizando produto
		@PutMapping
		public ResponseEntity<ProdutoFarmacia> putProdutoFarma(@RequestBody ProdutoFarmacia produtofarm){
			
			return ResponseEntity.status(HttpStatus.OK).body(produtosFarmaciaRepository.save(produtofarm));
			
		}
		
		
		//delete deletando produto da tabela
		@DeleteMapping("/{id}")
		public void deleteProdutoFarma(@PathVariable long id) {
			
			produtosFarmaciaRepository.deleteById(id);
		}
		
		
		
		
		
		
		
		

}
