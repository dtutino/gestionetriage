package it.prova.gestionetriage.service;

import java.util.List;

import it.prova.gestionetriage.model.Paziente;

public interface PazienteService {
	
	public Paziente inserisciNuovo(Paziente transientInstance);

	public List<Paziente> listAll();

	public Paziente cariscaSingoloElemento(Long id);
	
	public Paziente cercaPerCodiceFiscale(String codiceFiscaleInput);

}
