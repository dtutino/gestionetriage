package it.prova.gestionetriage.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dottore")
public class Dottore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "nome")
	private String nome;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "cognome")
	private String cognome;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "codiceDipendente")
	private String codiceDipendente;
	
    @OneToOne(targetEntity = Paziente.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true, name = "paziente_id")
	private Paziente paziente;
	
	public Dottore() {
		super();
	}
	
	public Dottore(Long id) {
		super();
		this.id = id;
	}
	
	public Dottore(Long id, String nome, String cognome, String codiceDipendente, Paziente paziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.paziente = paziente;
	}

	public Dottore(String nome, String cognome, String codiceDipendente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}

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
	public String getCodiceDipendente() {
		return codiceDipendente;
	}
	public void setCodiceDipendente(String codiceDipendente) {
		this.codiceDipendente = codiceDipendente;
	}

	public Paziente getPaziente() {
		return paziente;
	}

	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	
}
