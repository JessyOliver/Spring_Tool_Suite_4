package br.com.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto_farmacia")
public class ProdutoFarmacia {
	
	//criando atributos
		//criando chave PK
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@NotNull(message = "O campo nome é obrigatorio!")
		@Size(min = 5, max = 250, message = "O campo nome de ve conter no minimo 5 e no máximo 250 caracteres")
		private String nome;
		
		
		@NotNull(message = "O campo marca de ser preenchido.")
		@Size(min = 2, max = 100, message = "O campo marca deve conter no mínimo 2 e no maximo 100")
		private String marca;//o mesmo que tipo
		
		@NotNull(message = "O campo quantidade é obrigatorio!")
		@Positive
		private int quantidade;
		
		
		@NotNull(message = "O campo marca de ser preenchido.")
		@Positive
		private BigDecimal valor;//o mesmo que tipo
		
		
		//chave FK CategoriaFarmacia
		@ManyToOne
		@JsonIgnoreProperties("produto_farmac")
		private CategoriaFarmacia categoria_farmac;


		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getMarca() {
			return marca;
		}


		public void setMarca(String marca) {
			this.marca = marca;
		}


		public int getQuantidade() {
			return quantidade;
		}


		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}


		public BigDecimal getValor() {
			return valor;
		}


		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}


		public CategoriaFarmacia getCategoria_farmac() {
			return categoria_farmac;
		}


		public void setCategoria_farmac(CategoriaFarmacia categoria_farmac) {
			this.categoria_farmac = categoria_farmac;
		}
		
		
		
		

}
