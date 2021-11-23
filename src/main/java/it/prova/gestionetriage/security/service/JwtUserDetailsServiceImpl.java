package it.prova.gestionetriage.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.security.jwt.dto.JwtUserDetailsImpl;
import it.prova.gestionetriage.security.repository.UtenteRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UtenteRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente user = repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return JwtUserDetailsImpl.build(user);
	}
}