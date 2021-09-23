package br.com.santander.controllers;

import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.santander.AgendaApplication;
import br.com.santander.model.DTO.FormDto;
import br.com.santander.model.DTO.TokenDto;
import br.com.santander.service.security.TokenService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	private static Logger logger = LoggerFactory.getLogger(AgendaApplication.class);

	@Autowired
	private AuthenticationManager authManeger;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("auth")
	public ResponseEntity<?> autenticar(@RequestBody @Valid FormDto formDto) throws AuthenticationException{

		UsernamePasswordAuthenticationToken dadosLogin = formDto.converter();
		try {
			Authentication authentication = authManeger.authenticate(dadosLogin);
			
			String token = tokenService.gerartoken(authentication);
			logger.info("token: [" + token + "]");
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
