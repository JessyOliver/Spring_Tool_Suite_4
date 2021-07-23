package br.org.generation.helloworldatividadedois.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloAtiviDois")
public class AtividadeDoisController {
	
	@GetMapping
	public String helloAtiviDois(String titulo, String resposta) {
		
		
		titulo = "<h1 style=\"color: #F5F5F5; font-size: 30pt; background-color: #000080; text-align: center; font-family: Helvetica, Times, serif;\" >"
						+ " Objetivos de aprendizagem para essa semana: </h1> "
						+ "<h2 style=\"color: #F0F8FF; font-size: 30pt;  background-color: #7B68EE; text-align: left; font-family: Helvetica, Times, serif; \"> "
						+ "ESTUDAR </h2>";
		
		resposta = "<ul style=\"color: #F5F5F5; font-size: 20pt;  background-color: #00CED1;  text-align: left; font-family: Helvetica, Times, serif; \" >"
						+ "<li> Spring Tool</li>"
						+ "<li> Style de telas</li>"
						+ "<li> Colocar em Prática</li> </ul>";
		
		return titulo + resposta;
		
			
	}

}
