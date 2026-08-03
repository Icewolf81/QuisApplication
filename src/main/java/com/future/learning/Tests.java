package com.future.learning;

import java.util.Scanner;
/*
	Klasse zum Testen von Input und parse Methoden.
	Methoden zur Eingabe eines Strings über die Konsole und parsen in int und double.
 */

public class Tests
{
	// Erzeugen eines Scannerobjekts für die gesamte Klasse.
	private static final Scanner SC = new Scanner(System.in);

	// Methode zur Eingabe über die Konsole.
	public static String getInput()
	{
		System.out.println("Eingabe: ");
		return SC.nextLine();
	}

	// Methode zum Parsen eines Strings in ein int.
	public static int parseInputInt()
	{
		// "Lange" Schreibweise, zuerst in eine Variable zahl speichern und dann rückgeben.
		int zahl = Integer.parseInt(getInput());
		return zahl;
	}

	public static double parseInputDouble()
	{
		// "Kurze" Schreibweise, ohne in eine Variable zu speichern, direkt zurückgeben.
		return Double.parseDouble(getInput().replace(',','.'));
	}


}
