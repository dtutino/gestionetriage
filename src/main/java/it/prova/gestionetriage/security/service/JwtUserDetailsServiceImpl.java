package it.prova.gestionetriage.security.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.security.jwt.dto.JwtUserDetailsImpl;
import it.prova.gestionetriage.security.repository.UtenteRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService, UtenteService {

	@Autowired
	private UtenteRepository repo;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente user = repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return JwtUserDetailsImpl.build(user);
	}

	@Override
	public Utente inserisciNuovo(Utente transientInstance) {
		transientInstance.setStatoUtente(StatoUtente.DISABILITATO);
		transientInstance.setPassword(encoder.encode(transientInstance.getPassword())); 
		transientInstance.setDataRegistrazione(new Date());
		return repo.save(transientInstance);
	}

	@Override
	public List<Utente> listAll() {
		return (List<Utente>) repo.findAll();
	}

	@Override
	public Utente cariscaSingoloElemento(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Element with id " + id + " not found."));
	}

	@Override
	public Utente aggiorna(Utente utenteInstance) {
		return repo.save(utenteInstance);
	}

	@Override
	public void abilitaDisabilita(Utente input) {
		if(input.getStatoUtente().equals(StatoUtente.ABILITATO)) {
			input.setStatoUtente(StatoUtente.DISABILITATO);
		} else {
			input.setStatoUtente(StatoUtente.ABILITATO);
		}
		repo.save(input);
	}
}