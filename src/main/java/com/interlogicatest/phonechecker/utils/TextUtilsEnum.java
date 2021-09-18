package com.interlogicatest.phonechecker.utils;

/**
 * This enum contains all the texts that are used in the application.
 * 
 * @author Fulvio Zecchin
 *
 */
public enum TextUtilsEnum {
	
	//Correction text
	ADDED_PREFIX("Aggiunto il prefisso (27) al numero."),
	REMOVED_EXCESS("Identificato numero valido e rimosso ciò che e' primta/dopo le 11 cifre."),

	//Error text
	CONTAINS_WORDS("Il numero contiene del testo"),
	FEW_DIGITS("Il numero e' troppo corto (Lunghezza con l'aggiunta del prefisso e' minore di 11)."),
	TOO_MANY_DIGITS("Il numero e' troppo grande (Lunghezza maggiore di 11)."),
	WRONG_SUFFIX("Il numero non e' nel formato corretto. Il prefisso e' errato."),
	GENERIC_ERROR("Errore generico. Il numero non e' nel formato corretto (contiene simboli e/o lettere)."),
	
	//Text for generated file column header
	ROW_ID_HEADER("ID del numero validato"),
	NUMBER_HEADER("Numero"),
	CORRECTION_HEADER("Modifica/Aggiunta effettuata"),
	ERROR_HEADER("Motivo di scarto")
	;
	
	TextUtilsEnum(final String text) {
		this.text = text;
	}

	private String text;

	public String getText() {
		return text;
	}
}
