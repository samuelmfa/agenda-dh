package br.com.santander.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
