package br.org.generation.blogpessoalje.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.generation.blogpessoalje.model.Usuario;
import br.org.generation.blogpessoalje.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(username);
		usuario.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
					
		return usuario.map(UserDetailsImpl::new).get();
	}

}
