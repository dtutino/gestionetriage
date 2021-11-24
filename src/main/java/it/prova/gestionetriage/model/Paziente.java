package it.prova.gestionetriage.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "paziente")
public class Paziente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 50)
	@NotNull
	@Size(min = 2, max = 50)
	private String nome;
	
	@Column(name = "cognome", length = 50)
	@NotNull
	@Size(min = 2, max = 50)
	private String cognome;
	
	@Column(name = "codiceFiscale", length = 50)
	@NotNull
	@Size(min = 2, max = 50)
	private String codiceFiscale;
	
	@Column(name = "statoPaziente")
	@Enumerated(EnumType.STRING)
	private StatoPaziente statoPaziente;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dottore_id", referencedColumnName = "id")
	private Dottore dottore;

	public Paziente() {
		super();
	}

	public Paziente(Long id, @NotNull @Size(min = 2, max = 50) String nome,
			@NotNull @Size(min = 2, max = 50) String cognome, @NotNull @Size(min = 2, max = 50) String codiceFiscale,
			StatoPaziente statoPaziente, Dottore dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public StatoPaziente getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}

	public Dottore getDottore() {
		return dottore;
	}

	public void setDottore(Dottore dottore) {
		this.dottore = dottore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
