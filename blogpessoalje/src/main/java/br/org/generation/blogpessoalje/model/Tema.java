package br.org.generation.blogpessoalje.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_tema")
public class Tema {
	
	//criando atributos da tabela
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@NotNull(message = "O campo titulo é  obrigatório!")
		@Size(min = 10, max = 100, message = "O campo texto deve conter no mínimo 10 e no máximo 100 caracteres.")
		private String descricao;

		
		@Transient
		private int qtdTema;
		

		//relacionamento com a tabela Poatgem
		@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
		@JsonIgnoreProperties("tema")
		private  List<Postagem> postagem;

		//gets e sets 
		public List<Postagem> getPostagem() {
			return postagem;
		}

		public void setPostagem(List<Postagem> postagem) {
			this.postagem = postagem;
		}

		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		
		public int getQtdTema() {
			return qtdTema;
		}

		public void setQtdTema(int qtdTema) {
			this.qtdTema = qtdTema;
		}
	
	
}
