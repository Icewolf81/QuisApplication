package com.future.learning.handler;

import com.future.learning.SingleChoiceFrage;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class QuestionLoadHandler implements CoRHandler
{
    private QuestionLoadHandler nextHandler;

    public void linkWith(QuestionLoadHandler nextHandler)
    {
        this.nextHandler = nextHandler;
    }

    public abstract List<SingleChoiceFrage> handle(int anzahl) throws QuestionLoadException;

    public List<SingleChoiceFrage> handleNext(int anzahl) throws QuestionLoadException
    {
        if (nextHandler == null)
        {
            throw new QuestionLoadException("CoR findet keinen weiteren Handler!");
        }

        return nextHandler.handle(anzahl);
    }

    protected List<SingleChoiceFrage> parseFragen(String json)
    {
        Gson gson = new Gson();

        TriviaAntwort triviaAntwort =
                gson.fromJson(json, TriviaAntwort.class);

        if (triviaAntwort.responseCode != 0)
        {
            throw new IllegalStateException(
                    "Trivia DB meldet den Fehlercode: "
                            + triviaAntwort.responseCode
            );
        }

        List<SingleChoiceFrage> fragen = new ArrayList<>();

        for (TriviaFragenDaten frage : triviaAntwort.results)
        {
            List<String> antworten =
                    new ArrayList<>(frage.falscheAntworten());

            antworten.add(frage.richtigeAntwortText());

            Collections.shuffle(antworten);

            int richtigeAntwort =
                    antworten.indexOf(frage.richtigeAntwortText()) + 1;

            fragen.add(new SingleChoiceFrage(
                    frage.frage(),
                    antworten,
                    richtigeAntwort
            ));
        }

        return fragen;
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