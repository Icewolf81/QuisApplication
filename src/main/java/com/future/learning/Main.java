package com.future.learning;

import com.future.learning.handler.QuestionLoadException;

import java.util.Scanner;

// Startklasse der Anwendung.
public class Main
{
	public static void main(String[] args) throws QuestionLoadException
    {
		/*
		 * Frühere Konsolenversion des Quiz.
		 * Sie bleibt auskommentiert erhalten, damit die Entwicklung von der
		 * Konsolenausgabe zur grafischen Oberfläche nachvollziehbar bleibt.
		 */

//		Scanner input = new Scanner(System.in);
//
//		Quiz quiz = new Quiz();
//
//		quiz.addFrage(new MultipleChoiceFrage("Was ist Java",
//				List.of("Eine Insel",
//						"Eine Programmiersprache",
//						"Ein Festival",
//						"Existiert nicht"),
//				Set.of(1, 2)));
//		quiz.addFrage(new SingleChoiceFrage("Was ist HTML?",
//				List.of("Eine Abkürzung",
//						"Eine Person",
//						"Eine textbasierte Sprache",
//						"Eine Firma"),
//				3));
//
//		quiz.addFrage(new MultipleChoiceFrage("Was sind Betriebssysteme?",
//				List.of("Putzkräfte",
//						"Feuerwehr",
//						"Ärzte",
//						"Software"),
//				Set.of(1,4)));
//		quiz.addFrage(new WahrFalschFrage("Habt ihr heute Spaß?", true));
//
//		for (QuizFrage frage : quiz.getFragen())
//		{
//			// Zählen aller richtigen Antworten frage.isRichtig(auswahl)
//			if (frageBeantworten(frage, input))
//			{
//				quiz.addPunkte();
//			}
//			System.out.println("Richtige Antwort: " + frage.getRichtigeAntwort());
//
//		}
//		System.out.println(quiz.getPunktestand() + " / " + quiz.getFragen().size() + " richtig");

		// Erzeugt das Quizfenster und macht es für den Benutzer sichtbar.

//		Tests zur Hilfsklasse.
//		System.out.println(Tests.parseInputDouble());
//		System.out.println(Tests.parseInputInt());



//		ApiQuestionHandler apiHandler = new ApiQuestionHandler();
//		SavedQuestionHandler savedHandler = new SavedQuestionHandler();
//		ErrorQuestionHandler errorHandler = new ErrorQuestionHandler();
//
//		apiHandler.linkWith(savedHandler);
//		savedHandler.linkWith(errorHandler);
//
//		apiHandler.handle(5);

		StartGui gui = new StartGui();
		gui.setVisible(true);
//
//		final List<SingleChoiceFrage> fragen = TriviaFragenLoader.ladeFragen();
//		System.out.println(fragen.toString());
//		fragen.get(0).anzeigen();
//		fragen.get(1).anzeigen();

		// Versucht zunächst einen request zu machen und den body in der Konsole auszugeben!
//		final String API_URL =
//				"https://opentdb.com/api.php?amount=10&type=multiple";
//
//		HttpClient client = HttpClient.newHttpClient();
//
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create(API_URL))
//				.GET()
//				.build();
//
//		try
//		{
//			HttpResponse<String> response = client.send(
//					request,
//					HttpResponse.BodyHandlers.ofString()
//			);
//
//			System.out.println(response.body());
//		}
//		catch (IOException | InterruptedException e)
//		{
//			e.printStackTrace();
//		}

	}

	// Zeigt eine Frage in der Konsole an, liest die Eingabe und wertet sie aus.
	public static boolean frageBeantworten(QuizFrage frage, Scanner input)
	{
		frage.anzeigen();
		String eingabe = input.nextLine();
		return frage.isRichtigeAuswahl(eingabe);
	}
}
