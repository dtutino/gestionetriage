package it.prova.gestionetriage.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Dottore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreRequestDTO {
	
	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	@NotBlank(message = "{codiceDipendente.notblank}")
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String codiceDipendente;

	private Long Dottore;

	public DottoreRequestDTO() {
		super();
	}

	public DottoreRequestDTO(Long id) {
		super();
		this.id = id;
	}

	public DottoreRequestDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceDipendente.notblank}") @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codiceDipendente,
			Long Dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.Dottore = Dottore;
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

	public Long getDottore() {
		return Dottore;
	}

	public void setDottore(Long Dottore) {
		this.Dottore = Dottore;
	}
	
	public static DottoreRequestDTO buildDottoreRequestDTOFromModel(Dottore DottoreModel) {
		DottoreRequestDTO result = new DottoreRequestDTO(DottoreModel.getId(), DottoreModel.getNome(),
				DottoreModel.getCognome(), DottoreModel.getCodiceDipendente(),
				DottoreModel.getPaziente().getId());

		return result;
	}
	
	public static List<DottoreRequestDTO> createDottoreRequestDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(utenteEntity -> {
			return DottoreRequestDTO.buildDottoreRequestDTOFromModel(utenteEntity);
		}).collect(Collectors.toList());
	}

}
