package it.prova.gestionetriage.security.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetriage.dto.UtenteDTO;
import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.security.controller.exception.IdNotNullForInsertException;
import it.prova.gestionetriage.security.controller.exception.UtenteNotFoundException;
import it.prova.gestionetriage.security.service.JwtUserDetailsServiceImpl;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {
	
	@Autowired
	private JwtUserDetailsServiceImpl service;
	
	@GetMapping
	public List<UtenteDTO> getAll() {
		return UtenteDTO.createUtenteDTOListFromModelList(service.listAll());
	}
	
	@GetMapping("/{idInput}")
	public UtenteDTO getUtenteById(@PathVariable(required = true) Long idInput) {
		Utente utente = service.cariscaSingoloElemento(idInput);
		
		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + idInput);
		
		return UtenteDTO.buildUtenteDTOFromModel(utente);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UtenteDTO createNew(@Valid @RequestBody UtenteDTO utenteInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer) non sta bene		
		if (utenteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		
		Utente utenteInserito = service.inserisciNuovo(utenteInput.buildUtenteModel(true));
		return UtenteDTO.buildUtenteDTOFromModel(utenteInserito);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void abilitaDisabilita(@PathVariable(required = true) Long id) {
		Utente utente = service.cariscaSingoloElemento(id);

		if (utente == null)
			throw new UtenteNotFoundException("Utente not found con id: " + id);

		service.abilitaDisabilita(utente);
	}

}
