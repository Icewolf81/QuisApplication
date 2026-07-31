package com.future.learning;

// Fragetyp mit den beiden möglichen Antworten Wahr und Falsch.
public class WahrFalschFrage extends QuizFrage
{
	// Speichert, ob die Aussage wahr oder falsch ist.
	private final boolean richtigeAntwort;

	public WahrFalschFrage(String frage, boolean richtigeAntwort)
	{
		super(frage);
		this.richtigeAntwort = richtigeAntwort;
	}

	// Gibt die Frage mit den beiden festgelegten Antwortmöglichkeiten aus.
	@Override
	public void anzeigen()
	{
		System.out.println(getFrage());
		System.out.println("1: Wahr");
		System.out.println("2: Falsch");
		System.out.println("Wähle eine Antwort:");
	}

	// Wandelt die Auswahl 1 in true und jede andere Auswahl in false um.
	@Override
	public boolean isRichtig(int auswahl)
	{
		boolean antwort;
		if (auswahl == 1)
		{
			antwort = true;
		}
		else
		{
			antwort = false;
		}
		return this.richtigeAntwort == antwort;
	}

	// Der ternäre Operator liefert abhängig vom boolean-Wert den passenden Text.
	@Override
	public String getRichtigeAntwort()
	{
		return this.richtigeAntwort ? "Wahr" : "Falsch";
	}

	// Verarbeitet die Konsoleneingabe und vergleicht sie mit der richtigen Antwort.
	@Override
	public boolean isRichtigeAuswahl(String input)
	{
		try
		{
			int auswahl = Integer.parseInt(input);
			boolean antwort = auswahl == 1;
			return antwort == this.richtigeAntwort;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
}
