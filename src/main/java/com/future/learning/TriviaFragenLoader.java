package com.future.learning;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriviaFragenLoader
{
	public static List<SingleChoiceFrage> ladeFragen()
	{
		Path datei =
				Path.of("src", "main", "resources", "fragen2.json");

		try (InputStream input = Files.newInputStream(datei);
		     InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8))
		{
			Gson gson = new Gson();

			TriviaAntwort triviaAntwort = gson.fromJson(reader, TriviaAntwort.class);

			if (triviaAntwort.responseCode != 0)
			{
				throw new IllegalStateException("Trivia DB meldet den Fehlercode: "	+ triviaAntwort.responseCode);
			}

			List<SingleChoiceFrage> fragen = new ArrayList<>();

			for (TriviaFragenDaten frage : triviaAntwort.results)
			{
				// Falsche Antworten übernehmen.
				List<String> antworten = new ArrayList<>(frage.falscheAntworten());

				// Richtige Antwort hinzufügen.
				antworten.add(frage.richtigeAntwortText());

				// Antworten mischen.
				Collections.shuffle(antworten);

				// Position der richtigen Antwort ermitteln.
				// +1, weil unsere Antworten bei 1 beginnen.
				int richtigeAntwort = antworten.indexOf(frage.richtigeAntwortText()) + 1;

				fragen.add(new SingleChoiceFrage(frage.frage(), antworten, richtigeAntwort));
			}

			return fragen;
		}
		catch (Exception e)
		{
			throw new IllegalStateException("Die Trivia-Fragen konnten nicht geladen werden.", e);
		}
	}

	// Normale Klasse
	private static class TriviaAntwort
	{
		@SerializedName("response_code")
		int responseCode;

		List<TriviaFragenDaten> results;
	}

	// Record
	private record TriviaFragenDaten(
			@SerializedName("question")
			String frage,

			@SerializedName("correct_answer")
			String richtigeAntwortText,

			@SerializedName("incorrect_answers")
			List<String> falscheAntworten)
	{
	}
}