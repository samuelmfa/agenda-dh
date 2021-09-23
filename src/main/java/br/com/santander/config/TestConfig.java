package br.com.santander.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.santander.service.DBService;

@Configuration
public class TestConfig  {

	@Autowired
	DBService dbService;

	@Bean
	public boolean instanciateDataBase() {
		dbService.inserirUsuario();
		return true;
	}

}
