package br.com.santander.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.santander.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
