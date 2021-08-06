package br.com.minhalojadegame.controller;

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

import br.com.minhalojadegame.model.Produto;
import br.com.minhalojadegame.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	
//	@Autowired
//	ProdutosService produtosService;
//	
//	@RequestMapping(value = "produto", method = RequestMethod.GET)
//	public ModelAndView getpost() {
//		
//		ModelAndView mv = new ModelAndView("produto");
//		List<Produto> produto = produtosService.findAll();
//		mv.addObject("produto", produto);
//		return mv;
//	}
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//getAll todos os dados da tabela
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	//getById campo pelo id
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById( @PathVariable long id){
		
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.notFound().build());
	}
	
	//getByTitulo buscar palavra chave no campo titulo 
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String titulo){
		
		return ResponseEntity.ok(produtoRepository.findAllByTituloContainingIgnoreCase(titulo));
		
	} 
	
	
	//post criando produto
	@PostMapping
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
			
	}
	
	
	//put atualizando produto
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
		
	}
	
	//delete deletando produto da tabela
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {
		
		produtoRepository.deleteById(id);
	}
	
	
	
	
	
	
	
}
