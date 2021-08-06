package br.com.minhalojadegame.model;

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
@Table(name = "tb_categoria")
public class Categoria {
	
	
	//criando atributos
	//criando chave PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "O campo descrição é obrigatorio!")
	@Size(min = 5, max = 500, message = "O campo descrição de ve conter no minimo 5 e no máximo 500 caracteres")
	 private String descricao;
	
	
	@NotNull(message = "O campo plataforma de ser preenchido.")
	@Size(min = 2, max = 250, message = "O campo plataforma deve conter no mínimo 2 e no maximo 250")
	private String plataforma;//o mesmo que tipo
	
	//chave FK 1:N
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL )
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;

	
	//get set
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

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}






}//fim tudo
