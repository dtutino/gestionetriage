package it.prova.gestionetriage.repository;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetriage.model.Dottore;

public interface DottoreRepository extends CrudRepository<Dottore, Long> {
	
	public Dottore findByCodiceDipendente(String codiceDipendente);

}
