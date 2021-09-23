package br.com.santander.service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.santander.model.Email;
import br.com.santander.repositories.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private EmailRepository repository;

	public Email find(Long id) {
		Optional<Email> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Email.class.getName(), null));
	}

	public Email insert(Email Email) {
		return repository.save(Email);
	}

	public Email update(Email obj) {
		Email newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possivel excluir esse Email");
		}

	}

	private void updateData(Email newObj, Email obj) {
		newObj.setEmail(obj.getEmail());
	}

}
