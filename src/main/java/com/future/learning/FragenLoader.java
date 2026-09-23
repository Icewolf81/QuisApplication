package com.future.learning;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FragenLoader
{
	public static List<SingleChoiceFrage> ladeFragen()
	{
		InputStream input = FragenLoader.class.getResourceAsStream("/fragen.json");


		if (input == null)
		{
			throw new IllegalStateException("Die Datei kann nicht gefunden werden.");
		}

		try (InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8))
		{
			Gson gson = new Gson();
			Type listType = new TypeToken<List<FragenDaten>>() {}.getType();
			List<FragenDaten> daten = gson.fromJson(reader, listType);
			List<SingleChoiceFrage> fragen = new ArrayList<>();

			for (FragenDaten frage : daten)
			{
				fragen.add(new SingleChoiceFrage(
						frage.frage,
						frage.antworten,
						frage.richtigeAntwort)
				);
			}
			return fragen;
		}
		catch (Exception e)
		{
			throw new  IllegalStateException("Die Frage konnte nicht geladen werden.");
		}

	}

	private class FragenDaten
	{
		String frage;
		List<String> antworten;
		int richtigeAntwort;
	}

}
