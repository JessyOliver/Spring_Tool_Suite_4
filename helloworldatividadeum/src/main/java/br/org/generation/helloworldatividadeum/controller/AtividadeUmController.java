package br.org.generation.helloworldatividadeum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/helloAtividadeUm")
public class AtividadeUmController {

		@GetMapping
		public String  helloAtividadeUm(String habilidade, String mentalidade, String lista1,  String lista2) {
			
			 //String habilidade, mentalidade,lista1, lista2;
			
			habilidade = " <h1 style=\"color: #8B4513;font-size: 30pt; background-color: #FFD700; text-align: center; font-family: Helvetica, Times, serif; \" >"
							+ "HABILIDADE:</h1> ";
			lista1 = "<ol style=\"color: #FF4500;font-size: 20pt; background-color: #F0E68C; font-family: Times New Roman, Times, serif;\">"
							+ " <li> Orientação ao Futuro </li> "
							+ "<li> Responsabilidade Pessoal </li>"
							+ " <li> Persistencia</li> </ol>";
			
			mentalidade = "<h1 style=\"color: #8B4513;font-size: 30pt; background-color: #FFD700; text-align: center; font-family: Helvetica, Times, serif; \">"
							+ "MENTALIDADE:</h1>";
			
			lista2 = "<ol style=\"color: #FF4500;font-size: 20pt; background-color: #F0E68C; font-family:Times New Roman , Times, serif; \">"
							+ " <li> Atenção aos detalhes </li> "
							+ "<li> Proatividade </li> "
							+ "<li> Comunicação</li> </ol>";
							
			
			return  habilidade + lista1 + mentalidade + lista2;
			
		}
}
