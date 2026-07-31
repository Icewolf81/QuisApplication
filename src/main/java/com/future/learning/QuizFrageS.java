package com.future.learning;

// Frühere, nicht vererbte Version einer Quizfrage für die Konsolenanwendung.
public class QuizFrageS
{
//	private String frage; // Die Attribute haben den Zugriffsmodifikator private, damit sie außerhalb der Klasse nicht direkt gelesen oder verändert werden können.
//	private String antwort1; // Ein indirekter Zugriff könnte über Getter und Setter ermöglicht werden, ist in dieser Klasse jedoch nicht vorgesehen.
//	private String antwort2;
//	private String antwort3;
//	private String antwort4;
//	private int richtigeAntwort;

	// Instanzattribute: Jede Frage besitzt ihren eigenen Text und eigene Antworten.
	private String frage;
	private String[] antworten;
	private int richtigeAntwort;

	// Klassenattribut: Der Zähler wird von allen QuizFrageS-Objekten gemeinsam genutzt.
	private static int counter = 0;

	// Konstruktor für QuizFrageS mit dem Fragetext, vier Antworten und der Lösungsnummer.
	public QuizFrageS(String frage, String antwort1, String antwort2, String antwort3, String antwort4, int richtigeAntwort)
	{
		this.frage = frage;

		// Fasst die vier einzelnen Konstruktorparameter in einem Array zusammen.
		this.antworten = new String[]{
				antwort1,
				antwort2,
				antwort3,
				antwort4
		};
		this.richtigeAntwort = richtigeAntwort;

//		this.frage = argfrage; // this.frage bezeichnet das Attribut frage des aktuellen QuizFrage-Objekts. Ihm wird der Wert des Parameters argfrage zugewiesen.
//		this.antwort1 = argantwort1;
//		this.antwort2 = argantwort2;
//		this.antwort3 = argantwort3;
//		this.antwort4 = argantwort4;
//		this.richtigeAntwort = argrichtigeAntwort;
	}

	// Gibt die Frage und alle Antworten nummeriert in der Konsole aus.
	public void anzeigen()
	{
		System.out.println(this.frage);

		for (int i = 0; i < this.antworten.length; i++)
		{
			System.out.println((i + 1) + ": " + this.antworten[i]);
		}

//		System.out.println(this.frage); // Die Methode println() gibt den Wert des Attributs frage des aktuellen QuizFrage-Objekts in der Konsole aus.
//		System.out.println("1: " + this.antwort1);
//		System.out.println("2: " + this.antwort2);
//		System.out.println("3: " + this.antwort3);
//		System.out.println("4: " + this.antwort4);
	}

	// Vergleicht die eingegebene Antwortnummer mit der gespeicherten Lösungsnummer.
	public boolean isRichtig(int auswahl)
	{
		return auswahl == this.richtigeAntwort;
	}

	// Gibt das Ergebnis aus und erhöht bei einer richtigen Antwort den Gesamtzähler.
	public void auswertung(int auswahl)
	{
		if (isRichtig(auswahl))
		{
			counter++;
			System.out.println("Richtig!");
		}
		else
		{
			System.out.println("Leider falsch!");
		}
	}

	// Liefert den von allen Objekten gemeinsam verwendeten Zähler zurück.
	public static int getCounter()
	{
		return counter;
	}

	// Gibt nach Abschluss des Quiz die Zahl der richtigen Antworten aus.
	public static void endAuswertung()
	{
		System.out.println("Du hast " + getCounter() + " Fragen richtig beantwortet.");
	}

	// Nutzt richtigeAntwort - 1, weil Array-Indizes bei 0 beginnen.
	public void getRichtigeAntwort()
	{
		System.out.println("Die Richtige Antwort ist: " + antworten[this.richtigeAntwort - 1]);
	}
}
