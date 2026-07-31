package com.future.learning;

import java.util.ArrayList;
import java.util.List;

// Verwaltet die Fragen eines Quiz und den bisher erreichten Punktestand.
public class Quiz
{
	// Sammlung aller Fragen, die zum Quiz gehören.
	private List<QuizFrage> fragen;

	/*
	 * static bedeutet, dass alle Quiz-Objekte denselben Punktestand verwenden.
	 * Dadurch kann die GUI den Wert über Quiz.getPunktestand() abrufen.
	 */
	private static int punktestand;

	public Quiz()
	{
		fragen = new ArrayList<>();
		punktestand = 0;
	}

	// Fügt der Fragenliste eine weitere Frage hinzu.
	public void addFrage(QuizFrage frage)
	{
		fragen.add(frage);
	}

	// Erhöht den Punktestand nach einer richtigen Antwort um einen Punkt.
	public void addPunkte()
	{
		punktestand++;
	}

	// Liefert den aktuellen gemeinsamen Punktestand zurück.
	public static int getPunktestand()
	{
		return punktestand;
	}

	// Ermöglicht den Zugriff auf die gespeicherten Quizfragen.
	public List<QuizFrage> getFragen()
	{
		return fragen;
	}
}
