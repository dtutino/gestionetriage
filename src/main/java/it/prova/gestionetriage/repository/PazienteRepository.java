package it.prova.gestionetriage.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetriage.model.Paziente;

public interface PazienteRepository extends CrudRepository<Paziente, Long> {
	
	public Paziente findByCodiceFiscale(String codiceFiscale);

}
