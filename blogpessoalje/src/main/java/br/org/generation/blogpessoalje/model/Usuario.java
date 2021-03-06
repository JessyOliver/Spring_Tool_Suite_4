package br.org.generation.blogpessoalje.model;

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

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	//criando os atributos da tabela
	//chave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull(message = "Campo nome obrigatório!")
	@Size(min = 2, max = 255, message = "O campo nome deve conter no minimo 10 e no maximo 255 caracteres.")
	private String nome;
	
	private String imagem_perfil;
	
	private String tipo;
	
	@NotNull(message = "O campo e-mail é obrigatório.")
	@ApiModelProperty(example = "email@email.com.br")
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
	
	// relacionamento com a tabela postagem
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)//só irá afetar a postagem caso de remover o usuario
	@JsonIgnoreProperties({"usuario","tema"})
	private List<Postagem> postagem;
	

	//construtores para teste
	public Usuario() {
		//construtorr vazio
	}

	public Usuario(long id,String nome, String usuario, String senha,LocalDate dataNascimento) {
		
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
	}

	
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

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getImagem_perfil() {
		return imagem_perfil;
	}

	public void setImagem_perfil(String imagem_perfil) {
		this.imagem_perfil = imagem_perfil;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
	//fim tudo
}
