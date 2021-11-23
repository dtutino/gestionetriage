package it.prova.gestionetriage.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.prova.gestionetriage.model.Authority;
import it.prova.gestionetriage.model.AuthorityName;
import it.prova.gestionetriage.model.Utente;

public class AuthorityDTO {
	
	private Long id;
	
	private AuthorityName name;
	
	private List<Utente> users;

	public AuthorityDTO(Long id, AuthorityName name, List<Utente> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}

	public AuthorityDTO(Long id, AuthorityName name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<Utente> getUsers() {
		return users;
	}

	public void setUsers(List<Utente> users) {
		this.users = users;
	}
	
	public static AuthorityDTO buildAuthorityDTOFromModel(Authority AuthorityModel) {
		return new AuthorityDTO(AuthorityModel.getId(), AuthorityModel.getName());
	}

	public static List<AuthorityDTO> createAuthorityDTOListFromModelSet(Set<Authority> modelListInput) {
		return modelListInput.stream().map(AuthorityEntity -> {
			return AuthorityDTO.buildAuthorityDTOFromModel(AuthorityEntity);
		}).collect(Collectors.toList());
	}
	
	public static List<AuthorityDTO> createAuthorityDTOListFromModelList(List<Authority> modelListInput) {
		return modelListInput.stream().map(AuthorityEntity -> {
			return AuthorityDTO.buildAuthorityDTOFromModel(AuthorityEntity);
		}).collect(Collectors.toList());
	}

}
