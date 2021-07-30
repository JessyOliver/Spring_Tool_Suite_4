package br.org.generation.blogpessoalje.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	//criando os atributos da tabela
	//chave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "Campo nome obrigatório!")
	@Size(min = 10, max = 255, message = "O campo nome deve conter no minimo 10 e no maximo 255 caracteres.")
	private String nome;
	
	@NotNull(message = "O campo e-mail é obrigatório.")
	@Size(min = 10, max = 100, message = "O campo e-mail deve conter no minimo 10 e no maximo 100 caracteres.")
	@Email
	private String email;
	
	@NotNull(message = "O campo senha é obrigatório!")
	@Size(min = 5, max = 255, message = "O campo senha deve conter no minimo 6 e no maximo 20 caracteres.")
	private String senha;
	
	// relacionamento com a tabela postagem
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	
	//get e set
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
	
	//fim tudo
}
