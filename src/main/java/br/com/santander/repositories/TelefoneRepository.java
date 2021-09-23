package br.com.santander.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.model.Telefone;


@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
