package it.prova.gestionetriage.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Dottore;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {
	
	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{codiceFiscale.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String codiceFiscale;

	private StatoPaziente statoPaziente;

	private Long dottore;

	public PazienteDTO() {
		super();
	}

	public PazienteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codiceFiscale,
			StatoPaziente statoPaziente, Long dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public PazienteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codiceFiscale,
			StatoPaziente statoPaziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
	}

	public PazienteDTO(@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codiceFiscale,
			StatoPaziente statoPaziente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.statoPaziente = statoPaziente;
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

	public Long getDottore() {
		return dottore;
	}

	public void setDottore(Long dottore) {
		this.dottore = dottore;
	}
	
	public Paziente buildPazienteModel() {
		Paziente result = new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.statoPaziente,
				new Dottore(this.dottore));
		return result;
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		PazienteDTO result = new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(),
				pazienteModel.getCognome(), pazienteModel.getCodiceFiscale(), pazienteModel.getStatoPaziente(),
				pazienteModel.getDottore().getId());

		return result;
	}
	
	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(utenteEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(utenteEntity);
		}).collect(Collectors.toList());
	}

}
