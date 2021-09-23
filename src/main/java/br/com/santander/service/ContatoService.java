package br.com.santander.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.santander.model.Contato;
import br.com.santander.repositories.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repository;

	public List<Contato> findAll() {
		List<Contato> obj = repository.findAll();
		return obj;
	}

	public Contato find(Long id) {
		Optional<Contato> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Contato.class.getName(), null));
	}

	public Contato insert(Contato contato) {
		return repository.save(contato);
	}

	public Contato update(Contato obj) {
		Contato newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possivel excluir esse contato");
		}

	}

	private void updateData(Contato newObj, Contato obj) {
		newObj.setNome(obj.getNome());
	}

}
