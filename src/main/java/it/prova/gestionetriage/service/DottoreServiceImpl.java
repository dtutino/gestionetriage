package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.repository.DottoreRepository;

@Service
public class DottoreServiceImpl implements DottoreService {
	
	@Autowired
	private DottoreRepository repo;

	@Override
	public Dottore inserisciNuovo(Dottore transientInstance) {
		return repo.save(transientInstance);
	}

	@Override
	public List<Dottore> listAll() {
		return (List<Dottore>) repo.findAll();
	}

	@Override
	public Dottore cariscaSingoloElemento(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Dottore cercaPerCodiceDipendente(String codiceDipendenteInput) {
		return repo.findByCodiceDipendente(codiceDipendenteInput);
	}

}
