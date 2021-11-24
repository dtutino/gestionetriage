package it.prova.gestionetriage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.prova.gestionetriage.model.Authority;
import it.prova.gestionetriage.model.AuthorityName;
import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.security.repository.AuthorityRepository;
import it.prova.gestionetriage.security.repository.UtenteRepository;
import it.prova.gestionetriage.service.DottoreService;
import it.prova.gestionetriage.service.PazienteService;

@SpringBootApplication
public class GestionetriageApplication {
	
	@Autowired
	private UtenteRepository userRepository;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private DottoreService dottoreService;
	@Autowired
	private PazienteService pazienteService;

	public static void main(String[] args) {
		SpringApplication.run(GestionetriageApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner initGestioneTriage() {
		return (args) -> {

			Utente user = userRepository.findByUsername("admin").orElse(null);

			if (user == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityAdmin = new Authority();
				authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
				authorityAdmin = authorityRepository.save(authorityAdmin);

				Authority authorityUser = new Authority();
				authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
				authorityUser = authorityRepository.save(authorityUser);

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityAdmin, authorityUser });

				user = new Utente();
				user.setAuthorities(authorities);
				user.setStatoUtente(StatoUtente.ABILITATO);
				user.setNome("Mario");
				user.setCognome("Rossi");
				user.setDataRegistrazione(new Date());
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("admin"));

				user = userRepository.save(user);

			}

			Utente commonUser = userRepository.findByUsername("commonUser").orElse(null);

			if (commonUser == null) {

				/**
				 * Inizializzo i dati del mio test
				 */

				Authority authorityUser = authorityRepository.findByName(AuthorityName.ROLE_SUB_OPERATOR);
				if (authorityUser == null) {
					authorityUser = new Authority();
					authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
					authorityUser = authorityRepository.save(authorityUser);
				}

				List<Authority> authorities = Arrays.asList(new Authority[] { authorityUser });

				commonUser = new Utente();
				commonUser.setAuthorities(authorities);
				commonUser.setStatoUtente(StatoUtente.ABILITATO);
				commonUser.setNome("Fabio");
				commonUser.setCognome("Gialli");
				commonUser.setDataRegistrazione(new Date());
				commonUser.setUsername("commonUser");
				commonUser.setPassword(passwordEncoder.encode("commonUser"));

				commonUser = userRepository.save(commonUser);

			}
			
			//Dottore dottorLuigi = dottoreService.cercaPerCodiceDipendente("DOTT-Luigi")
			
			Dottore dottorLuigi = new Dottore();
			dottorLuigi.setCodiceDipendente("DOTT-Luigi");
			dottorLuigi.setNome("Luigi");
			dottorLuigi.setCognome("Bianchi");
			
			dottorLuigi = dottoreService.inserisciNuovo(dottorLuigi);
			
			Paziente pazienteMario = new Paziente();
			pazienteMario.setNome("Mario");
			pazienteMario.setCognome("Rossi");
			pazienteMario.setCodiceFiscale("mrdfj335jsd");
			pazienteMario.setStatoPaziente(StatoPaziente.IN_VISITA);
			pazienteMario.setDottore(dottoreService.cariscaSingoloElemento(1L));
			
			pazienteMario = pazienteService.inserisciNuovo(pazienteMario);
			
		};
	}

}
