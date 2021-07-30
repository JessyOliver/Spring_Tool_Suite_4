package br.com.minhalojadegames.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto_game")
public class Produto {

	
	//atributos dessa tabela
	//chave PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O campo título é  obrigatório!")
	@Size(min = 5, max = 250, message = "o campo titulo deve conter no minimo 5 e no máximo 250 caracteres." )
	private String titulo;
	
	@NotNull(message = "O campo codigo é  obrigatório!")
	@Size(min = 7, max = 10, message = "O campo codigo deve conter no mínimo 7 e no maximo 10 caracteres.")
	private String codigo;
	
	
	@NotNull(message = "O campo quantidade deve ser preenchido.")
	private int quantidade;
	
	@NotNull(message = "O campo valor deve ser prenchido.")
	private BigDecimal valor;
	
	
	//chave FK N:1
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;


	//get set
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
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


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
	
	
	
	
}
