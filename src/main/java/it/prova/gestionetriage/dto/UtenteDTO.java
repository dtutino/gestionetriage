package it.prova.gestionetriage.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Authority;
import it.prova.gestionetriage.model.StatoUtente;
import it.prova.gestionetriage.model.Utente;
import it.prova.gestionetriage.validation.ValidationNoPassword;
import it.prova.gestionetriage.validation.ValidationWithPassword;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtenteDTO {

	private Long id;
	
	@NotBlank(message = "{nome.notblank}", groups = { ValidationWithPassword.class, ValidationNoPassword.class })
	private String nome;

	@NotBlank(message = "{cognome.notblank}", groups = { ValidationWithPassword.class, ValidationNoPassword.class })
	private String cognome;

	@NotBlank(message = "{username.notblank}", groups = { ValidationWithPassword.class, ValidationNoPassword.class })
	@Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String username;

	@NotBlank(message = "{password.notblank}", groups = ValidationWithPassword.class)
	@Size(min = 3, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
	private String password;

	private Date dataIscrizione;

	private StatoUtente statoUtente;

	private Long[] authorities;

	public UtenteDTO() {
	}

	public UtenteDTO(Long id,
			@NotBlank(message = "{nome.notblank}", groups = { ValidationWithPassword.class,
					ValidationNoPassword.class }) String nome,
			@NotBlank(message = "{cognome.notblank}", groups = { ValidationWithPassword.class,
					ValidationNoPassword.class }) String cognome,
			@NotBlank(message = "{username.notblank}", groups = { ValidationWithPassword.class,
					ValidationNoPassword.class }) @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String username,
			Date dataIscrizione, StatoUtente statoUtente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.dataIscrizione = dataIscrizione;
		this.statoUtente = statoUtente;
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

	public Date getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public StatoUtente getStatoUtente() {
		return statoUtente;
	}

	public void setStatoUtente(StatoUtente statoUtente) {
		this.statoUtente = statoUtente;
	}

	public Long[] getauthorities() {
		return authorities;
	}

	public void setauthorities(Long[] authorities) {
		this.authorities = authorities;
	}

	public Utente buildUtenteModel(boolean includeIdRoles) {
		Utente result = new Utente(this.id, this.nome, this.cognome, this.username, this.password, this.dataIscrizione,
				this.statoUtente);
		if (includeIdRoles && authorities != null)
			result.setAuthorities(Arrays.asList(authorities).stream().map(id -> new Authority(id)).collect(Collectors.toList()));

		return result;
	}

	// niente password...
	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
		UtenteDTO result = new UtenteDTO(utenteModel.getId(), utenteModel.getNome(),
				utenteModel.getCognome(), utenteModel.getUsername(), utenteModel.getDataRegistrazione(), utenteModel.getStatoUtente());

		if (!utenteModel.getAuthorities().isEmpty())
			result.authorities = utenteModel.getAuthorities().stream().map(r -> r.getId()).collect(Collectors.toList())
					.toArray(new Long[] {});

		return result;
	}
	
	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput) {
		return modelListInput.stream().map(utenteEntity -> {
			return UtenteDTO.buildUtenteDTOFromModel(utenteEntity);
		}).collect(Collectors.toList());
	}

}
