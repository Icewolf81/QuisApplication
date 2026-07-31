package com.future.learning;

// Gemeinsamer Vertrag für alle Fragetypen, die eine Texteingabe auswerten können.
public interface Beantwortbar
{
	// Prüft die übergebene Eingabe und liefert true zurück, wenn sie richtig ist.
	boolean isRichtigeAuswahl(String input);
}
