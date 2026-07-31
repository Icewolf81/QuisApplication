package com.future.learning;

/*
 * Abstrakte Basisklasse für alle Fragetypen.
 * Sie speichert den gemeinsamen Fragetext und legt fest, welche Methoden
 * jede konkrete Frage bereitstellen muss.
 */
public abstract class QuizFrage implements Beantwortbar
{
	// Der Fragetext wird nach dem Erzeugen des Objekts nicht mehr verändert.
	private final String frage;

	public QuizFrage(String frage)
	{
		this.frage = frage;
	}

	// Gibt den gespeicherten Fragetext zurück.
	public String getFrage()
	{
		return frage;
	}

	// Die konkrete Ausgabe hängt vom jeweiligen Fragetyp ab.
	public abstract void anzeigen();

	// Prüft eine als Zahl übergebene Antwort.
	public abstract boolean isRichtig(int auswahl);

	// Liefert die richtige Antwort als lesbaren Text zurück.
	public abstract String getRichtigeAntwort();
}
