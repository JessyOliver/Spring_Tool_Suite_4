package br.org.generation.blogpessoalje.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import br.org.generation.blogpessoalje.model.Usuario;
import br.org.generation.blogpessoalje.model.UsuarioLogin;
import br.org.generation.blogpessoalje.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	//cadastrando usuario
	public Usuario cadastrarUsuario(Usuario  usuario){
		
		//Lanço uma Exception do tipo Response Status Bad Request
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) 
		
			//verificando se o usuário já existe
			throw new  ResponseStatusException( HttpStatus.BAD_REQUEST, "Usuário já existe.", null);
			
		// Calcular a idade (em anos) através do método between, da Classe Period
			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
				
			//verificando se o usuário é maior de idade
			if (idade < 18)
				throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);

					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
					String senhaEncoder = encoder.encode(usuario.getSenha());
					usuario.setSenha(senhaEncoder);
			
						//return Optional.of(usuarioRepository.save(usuario));
					return usuarioRepository.save(usuario);

	}
	
	
	//metodo para atualizar cadastro
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> usuarioCad = usuarioRepository.findByUsuario(usuario.getUsuario());
			
			//verificando se usuario já tem o email de alteração cadastrado por outro usuário
			if (usuarioCad.isPresent() && (usuarioCad.get().getId() != usuario.getId())) {
				
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "E-mail do usuário já cadastrado!", null);
				
			} else {

				
				// Calcular a idade (em anos) através do método between, da Classe Period
				int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
					
				//verificando se o usuário é maior de idade
				if (idade < 18) {
					
					throw new ResponseStatusException(
							HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
				}
				else {	

						BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				
						String senhaEncoder = encoder.encode(usuario.getSenha());
						usuario.setSenha(senhaEncoder);
				
						return Optional.of(usuarioRepository.save(usuario));
				}
				
			}
			
		} else {
			
			//Lanço uma Exception do tipo Response Status Not Found

			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);

		}
		
	}
	
	
	//realizando o login do usuário
	public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin){
		//criptografia da senha
		BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder();		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
		//verificando se o usuário já existe
		if (usuario.isPresent()) {
			//autenticando usuario e senha
			//comparando a senha que o usuario digitou
			if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
					
				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);//pega o que criptogra e adicional basic
			
				//passando os dados para o usuario login
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setImagem_perfil(usuario.get().getImagem_perfil());
				usuarioLogin.get().setTipo(usuario.get().getTipo());			
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(authHeader);				

				return usuarioLogin;
			
		
			}
			
		}//fim if
	
			
			 // Lanço uma Exception do tipo Response Status Unauthorized
			//caso as informações sejam diferentes
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
		
	}
		
	
}//fim tudo
