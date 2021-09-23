package br.com.santander.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.santander.model.Usuario;
import br.com.santander.repositories.UsuarioRepository;

@Service
public class DetalheUsuarioServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

		if (usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário: [" + username + "] não encontrato");
		}

		return usuario.get();
	}

}
