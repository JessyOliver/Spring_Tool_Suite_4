package br.com.minhalojadegame.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.minhalojadegame.model.Usuario;
import br.com.minhalojadegame.model.UsuarioLogin;
import br.com.minhalojadegame.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//cadastrar usuario
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		
			if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) 
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe.", null);

				int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
			
				if (idade < 18) 
					
					throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos.", null);
									
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					
					String senhaEnconder = encoder.encode(usuario.getSenha());
					usuario.setSenha(senhaEnconder);
					
					return Optional.of(usuarioRepository.save(usuario));
			
	}
	
	
	//metodo para atualizar cadastro do usuario
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
			
			if (idade < 18) 
				
				throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos.", null);
				
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				
				String senhaesconder = encoder.encode(usuario.getSenha());
				usuario.setSenha(senhaesconder);
				
				return Optional.of(usuarioRepository.save(usuario));

		} else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);

		}
		
		
	}
	
	
	
	//realizando o login do usuario
	public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(authHeader);				

				return usuarioLogin;
			
		
			}
				
		} 
				
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "usuário ou senha inválidos!", null);

	}
	
	
	
	
}
