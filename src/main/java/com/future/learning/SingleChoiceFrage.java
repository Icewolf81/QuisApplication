package com.future.learning;

import java.util.List;

// Fragetyp, bei dem genau eine Antwort richtig ist.
public class SingleChoiceFrage extends QuizFrage
{
	// Enthält die Antwortmöglichkeiten in ihrer angezeigten Reihenfolge.
	private final List<String> antworten;

	// Nummer der richtigen Antwort; die Zählung beginnt für den Benutzer bei 1.
	private final int richtigeAntwort;

	public SingleChoiceFrage(String frage, List<String> antworten, int richtigeAntwort)
	{
		// Übergibt den gemeinsamen Fragetext an den Konstruktor der Oberklasse.
		super(frage);
		this.antworten = antworten;
		this.richtigeAntwort = richtigeAntwort;
	}

	// Gibt die Frage und ihre Antwortmöglichkeiten nummeriert in der Konsole aus.
	@Override
	public void anzeigen()
	{
		System.out.println(getFrage());

		for (int i = 0; i < this.antworten.size(); i++)
		{
			// i + 1 wandelt den Listenindex 0 bis 3 in die Anzeige 1 bis 4 um.
			System.out.println((i + 1) + ": " + this.antworten.get(i));
		}
		System.out.println("Wähle eine Antwort aus:");
	}

	// Vergleicht die ausgewählte Antwortnummer mit der richtigen Antwortnummer.
	@Override
	public boolean isRichtig(int auswahl)
	{
		return auswahl == this.richtigeAntwort;
	}

	// Der Listenindex liegt um eins unter der angezeigten Antwortnummer.
	@Override
	public String getRichtigeAntwort()
	{
		return this.antworten.get(richtigeAntwort - 1);
	}

	// Stellt der grafischen Oberfläche die Antworttexte zur Verfügung.
	public List<String> getAntworten()
	{
		return this.antworten;
	}

	// Wandelt eine Texteingabe in eine Ganzzahl um und prüft anschließend die Antwort.
	@Override
	public boolean isRichtigeAuswahl(String input)
	{
		try
		{
			int auswahl = Integer.parseInt(input);
			return auswahl == this.richtigeAntwort;
		}
		catch (NumberFormatException e)
		{
			// Nicht numerische Eingaben gelten als falsch.
			return false;
		}
	}
}
