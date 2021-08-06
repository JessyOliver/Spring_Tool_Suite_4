package br.com.minhalojadegame.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tb_usuario_ljgame")
public class Usuario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "O campo nome é obrigatório.")
	@Size( min = 2, max = 100, message = "O campo nome deve conter no minimo 10 e no maximo 100 caracteres.")
	private String nome;
	
	@NotNull(message = "O campo tipo é obrigatório.")
	@Size( min = 2, max = 100, message = "O campo tipo deve conter no minimo 10 e no maximo 100 caracteres.")
	private String tipo;
	
	@NotNull(message = "O campo usuario é obrigatório.")
	@Size( min = 2, max = 100, message = "O campo usuario deve conter no minimo 10 e no maximo 100 caracteres.")
	@Email
	private String usuario;//no lugar de email
	
	@NotNull(message = "O campo senha é obrigatório!")
	@Size(min = 5, message = "O campo senha deve conter no minimo 5")
	private String senha;
	
	/**
	 * Novo Atributo - Data de Nascimento
	 * Não esquecer de Gerar os métodos Get e Set
	 */

	@Column(name = "dt_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
	
	
	// relacionamento com a tabela produto
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)//só irá afetar a postagem caso de remover o usuario
	@JsonIgnoreProperties({"usuario","categoria"})
	private List<Produto> produto;


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


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public List<Produto> getProduto() {
		return produto;
	}


	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
	
}
