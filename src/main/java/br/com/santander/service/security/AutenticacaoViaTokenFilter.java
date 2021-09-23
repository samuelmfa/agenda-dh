package br.com.santander.service.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.santander.AgendaApplication;
import br.com.santander.model.Usuario;
import br.com.santander.service.UsuarioService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UsuarioService usuarioService;

	private static Logger logger = LoggerFactory.getLogger(AgendaApplication.class);

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioService usuarioService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = request.getHeader("Authorization");

		if (token != null) {

			logger.info("Valida token: [" + token + "]");

			token = recuperarToken(token);

			if (tokenService.isTokenValido(token)) {
				autenticarCliente(token);
			}

		}

		filterChain.doFilter(request, response);

	}

	private void autenticarCliente(String token) {

		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioService.find(idUsuario);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String recuperarToken(String token) {
		if (token.length() > 0) {
			return token.substring(7, token.length());
		}
		return null;

	}

}
