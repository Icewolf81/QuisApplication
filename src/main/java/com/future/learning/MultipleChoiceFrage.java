package com.future.learning;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Fragetyp, bei dem mehrere Antworten gleichzeitig richtig sein können.
public class MultipleChoiceFrage extends QuizFrage
{
	// Enthält die Texte aller auswählbaren Antworten.
	private final List<String> antworten;

	// Enthält die Nummern aller richtigen Antworten, beispielsweise 1 und 3.
	private final Set<Integer> richtigeAntworten;

	public MultipleChoiceFrage(String frage, List<String> antworten, Set<Integer> richtigeAntworten)
	{
		super(frage);
		this.antworten = antworten;
		this.richtigeAntworten = richtigeAntworten;
	}

	// Gibt die Frage und alle Antwortmöglichkeiten nummeriert in der Konsole aus.
	@Override
	public void anzeigen()
	{
		System.out.println(getFrage());

		for (int i = 0; i < this.antworten.size(); i++)
		{
			System.out.println((i + 1) + ": " + this.antworten.get(i));
		}
		System.out.println("Mehrere Antworten mit Komma trennen:");
	}

	/*
	 * Eine einzelne Ganzzahl reicht für diesen Fragetyp nicht aus.
	 * Die eigentliche Auswertung erfolgt deshalb in isRichtigeAuswahl(String).
	 */
	@Override
	public boolean isRichtig(int auswahl)
	{
		return false;
	}

	// Erstellt einen lesbaren Text mit allen richtigen Antwortnummern und -texten.
	@Override
	public String getRichtigeAntwort()
	{
		StringBuilder builder = new StringBuilder();

		for (int nummer : richtigeAntworten)
		{
			builder.append(nummer + ": " + antworten.get(nummer - 1) + "\n");
		}
		return builder.toString();
	}

	// Wandelt eine kommaseparierte Eingabe wie "1, 3" in eine Zahlenmenge um.
	@Override
	public boolean isRichtigeAuswahl(String input)
	{
		try
		{
			Set<Integer> auswahl = new HashSet<>();

			// Beispiel: Aus "3, 1,2" entsteht ein Array mit "3", " 1" und "2".
			String[] teile = input.split(",");

			for (String teil : teile)
			{
				// trim() entfernt Leerzeichen vor und hinter den einzelnen Zahlen.
				auswahl.add(Integer.parseInt(teil.trim()));
			}

			// Sets sind nur gleich, wenn sie genau dieselben Werte enthalten.
			return auswahl.equals(this.richtigeAntworten);
		}
		catch (NumberFormatException e)
		{
			// Eine nicht numerische Eingabe wird als falsche Antwort behandelt.
			return false;
		}
	}
}
