package it.prova.gestionetriage.service;

import java.util.List;

import it.prova.gestionetriage.model.Dottore;

public interface DottoreService {
	
	public Dottore inserisciNuovo(Dottore transientInstance);

	public List<Dottore> listAll();

	public Dottore cariscaSingoloElemento(Long id);
	
	public Dottore cercaPerCodiceDipendente(String codiceDipendenteInput);

}
