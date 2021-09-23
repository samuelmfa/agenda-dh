package br.com.santander.service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.santander.model.Telefone;
import br.com.santander.repositories.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository repository;

	public Telefone find(Long id) {
		Optional<Telefone> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Telefone.class.getName(), null));
	}

	public Telefone insert(Telefone Telefone) {
		return repository.save(Telefone);
	}

	public Telefone update(Telefone obj) {
		Telefone newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possivel excluir esse Telefone");
		}

	}

	private void updateData(Telefone newObj, Telefone obj) {
		newObj.setTelefone(obj.getTelefone());
	}

}
