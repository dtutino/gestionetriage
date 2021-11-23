package it.prova.gestionetriage.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatoUtente {
	
ABILITATO("ABILITATO"), DISABILITATO("DISABILITATO");
	
	private String descrizione;

	StatoUtente(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	@JsonCreator
	public static StatoUtente getStatoUtenteFromCode(String input) {
		if(StringUtils.isBlank(input))
			return null;
		
		for (StatoUtente statoUtenteItem : StatoUtente.values()) {
			if (statoUtenteItem.equals(StatoUtente.valueOf(input))) {
				return statoUtenteItem;
			}
		}
		return null;
	}

}
