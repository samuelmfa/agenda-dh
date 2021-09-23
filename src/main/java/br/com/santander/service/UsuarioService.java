package br.com.santander.service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.santander.AgendaApplication;
import br.com.santander.model.Usuario;
import br.com.santander.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repository;	

	private static Logger logger = LoggerFactory.getLogger(AgendaApplication.class);

	public Usuario find(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName(), null));
	}

	public Usuario insert(Usuario Usuario) {
		try {
			return repository.save(Usuario);
		} catch (Exception e) {
			logger.info("erro: " + e);
			return Usuario;
		}

	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possivel excluir esse Usuario");
		}
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setSenha(obj.getSenha());
		newObj.setRole(obj.getRole());
	}

}
