package it.prova.gestionetriage.security.service;

import java.util.List;

import it.prova.gestionetriage.model.Utente;

public interface UtenteService {
	
	public Utente inserisciNuovo(Utente transientInstance);

	public List<Utente> listAll();

	public Utente cariscaSingoloElemento(Long id);
	
	public Utente aggiorna(Utente utenteInstance);
	
	public void abilitaDisabilita(Utente input);

}
