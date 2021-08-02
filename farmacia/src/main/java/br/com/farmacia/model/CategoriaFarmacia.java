package br.com.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria_farmacia")
public class CategoriaFarmacia {
	
	//criando atributos
		//criando chave PK
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@NotNull(message = "O campo descrição é obrigatorio!")
		@Size(min = 5, max = 500, message = "O campo descrição de ve conter no minimo 5 e no máximo 500 caracteres")
		private String descricao;
		
		
		@NotNull(message = "O campo uso de ser preenchido.")
		@Size(min = 2, max = 100, message = "O campo uso deve conter no mínimo 2 e no maximo 250")
		private String uso;//o mesmo que tipo
		
		//chave FK
		@OneToMany(mappedBy = "categoria_farmac", cascade = CascadeType.ALL )
		@JsonIgnoreProperties("categoria_farmac")
		private List<ProdutoFarmacia> produto_farmac;

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

		public String getUso() {
			return uso;
		}

		public void setUso(String uso) {
			this.uso = uso;
		}

		public List<ProdutoFarmacia> getProduto_farmac() {
			return produto_farmac;
		}

		public void setProduto_farmac(List<ProdutoFarmacia> produto_farmac) {
			this.produto_farmac = produto_farmac;
		}
		
		
		
		

}
