package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.repository.PazienteRepository;

@Service
public class PazienteServiceImpl implements PazienteService {
	
	@Autowired
	private PazienteRepository repo;

	@Override
	public Paziente inserisciNuovo(Paziente transientInstance) {
		return repo.save(transientInstance);
	}

	@Override
	public List<Paziente> listAll() {
		return (List<Paziente>) repo.findAll();
	}

	@Override
	public Paziente cariscaSingoloElemento(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Paziente cercaPerCodiceFiscale(String codiceFiscaleInput) {
		return repo.findByCodiceFiscale(codiceFiscaleInput);
	}

}
