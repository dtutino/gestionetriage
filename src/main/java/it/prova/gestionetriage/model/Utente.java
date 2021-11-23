package it.prova.gestionetriage.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
	private Long id;
	
	@Column(name = "nome", length = 50)
	@NotNull
	@Size(min = 2, max = 50)
	private String nome;
	
	@Column(name = "cognome", length = 50)
	@NotNull
	@Size(min = 2, max = 50)
	private String cognome;
	
	@Column(name = "username", length = 50, unique = true)
	@NotNull
	@Size(min = 4, max = 50)
	private String username;
	
	@Column(name = "password", length = 100)
	@NotNull
	@Size(min = 4, max = 100)
	private String password;
	
	private Date dataRegistrazione;
	
	@Column(name = "enabled")
	@NotNull
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "utenti_authorities", joinColumns = {
			@JoinColumn(name = "utente_username", referencedColumnName = "username") }, inverseJoinColumns = {
					@JoinColumn(name = "authority_id", referencedColumnName = "id") })
	private List<Authority> authorities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}