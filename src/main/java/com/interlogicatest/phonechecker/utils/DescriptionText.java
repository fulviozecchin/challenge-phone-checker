package com.interlogicatest.phonechecker.utils;

/**
 * This enumeration contains all text values that indicate
 * the correction made to a number or the reason why it was discarded.
 * 
 * @author Fulvio Zecchin
 *
 */
public enum DescriptionText {
	
	//Correction text
	ADDED_PREFIX("Aggiunto il prefisso (27) al numero."),
	REMOVED_EXCESS("Identificato numero valido e rimosso ciò che e' primta/dopo le 11 cifre."),

	//Error text
	CONTAINS_WORDS("Il numero contiene del testo"),
	FEW_DIGITS("Il numero e' troppo corto (Lunghezza con l'aggiunta del prefisso e' minore di 11)."),
	TOO_MANY_DIGITS("Il numero e' troppo grande (Lunghezza maggiore di 11)."),
	WRONG_SUFFIX("Il numero non e' nel formato corretto. Il prefisso e' errato."),
	
	//Generic Default Error
	GENERIC_ERROR("Errore generico. Il numero non e' nel formato corretto (contiene simboli e/o lettere).")
	;
	
	DescriptionText(final String text) {
		this.text = text;
	}

	private String text;

	public String getText() {
		return text;
	}
}
