package com.future.learning;

import java.util.Scanner;
/*

Aufgabe 5:
Erzeuge drei verschiedene Objekte der Klasse "QuizFrage".

Beispielsweise:

- Java
- HTML
- Betriebssysteme

Lasse nacheinander alle drei Fragen anzeigen.

---

Bonus 1:
Erweitere "QuizFrage".

Mache die einzelnen Fragen-Objekte als List<QuizFrage> anstatt einzelnen Aufrufen!

---

Bonus 2:

Schreibe eine Methode

Java
public String getRichtigeAntwort()

Sie soll den Text der richtigen Antwort zurückgeben.

Beispiel:

"Eine Programmiersprache"

nicht:

2

---

Bonus 3:
Der Konstruktor soll prüfen, ob die richtige Antwort zwischen 1 und 4 liegt.

Ist dies nicht der Fall, soll

Java
throw new IllegalArgumentException(...)

ausgelöst werden.

Teste anschließend:

Java
QuizFrage frage = new QuizFrage(
    "...",
    "...",
    "...",
    "...",
    "...",
    9
);



 */

// Frühere Konsolenversion der ursprünglichen Quizaufgabe.
public class MainS
{
	public static void main(String[] args) // public und String[] args kann inzwischen weggelassen werden.
	{
		// Scanner liest die Antwortnummern des Benutzers aus der Konsole ein.
		Scanner input = new Scanner(System.in);

		// Erzeugt drei voneinander unabhängige Quizfragen.
		QuizFrageS frage1 = new QuizFrageS("Was ist Java? ", "Eine Insel", "Eine Programmiersprache", "Ein Festival", "Existiert nicht", 2);
		QuizFrageS frage2 = new QuizFrageS("Wofür wird HTML hauptsächlich verwendet? ", "Zur Gestaltung von Datenbanken", "Zur Verwaltung von Betriebssystemen", "Zur Strukturierung von Webseiten", "Zur Verschlüsselung von Dateien", 3);
		QuizFrageS frage3 = new QuizFrageS("Welche Aufgabe übernimmt ein Betriebssystem? ", "Es verwaltet Hardware und Programme", "Es erstellt ausschließlich Webseiten", "Es ersetzt den Arbeitsspeicher", "Es dient nur zum Schreiben von Texten", 1);

		// Gibt zu Testzwecken die richtigen Antworten aus.
		frage1.getRichtigeAntwort();
		frage2.getRichtigeAntwort();
		frage3.getRichtigeAntwort();

		// Zeigt jede Frage an, liest die Auswahl und wertet sie direkt aus.
		frage1.anzeigen();
		int auswahl = input.nextInt();
		frage1.auswertung(auswahl);

		frage2.anzeigen();
		auswahl = input.nextInt();
		frage2.auswertung(auswahl);

		frage3.anzeigen();
		auswahl = input.nextInt();
		frage3.auswertung(auswahl);

		// Gibt nach allen Fragen die Anzahl der richtigen Antworten aus.
		QuizFrageS.endAuswertung();
	}
}
