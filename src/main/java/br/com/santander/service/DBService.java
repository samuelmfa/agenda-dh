package br.com.santander.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.santander.model.Perfil;
import br.com.santander.model.Usuario;
import br.com.santander.repositories.PerfilRepository;

@Service
public class DBService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilRepository perfilRepository;

	private ArrayList<Perfil> perfils = new ArrayList<>();
	private Usuario usuario;

	public void inserirUsuario() {
		this.perfils.addAll(Arrays.asList(perfilRepository.save(new Perfil("ADMIN"))));
		this.usuario = new Usuario("admin@gmail.com", this.userPassword("123"), perfils);
		usuarioService.insert(usuario);
	}

	public String userPassword(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}

}
