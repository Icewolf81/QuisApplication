package com.future.learning.handler;

import com.future.learning.QuizFrage;

import java.util.List;

public class SavedQuestionHandler extends QuestionLoadHandler
{
    // TODO: Füge die Cached-Anfrage ein!
    @Override
    public List<QuizFrage> handle(int anzahl) throws QuestionLoadException
    {
        return handleNext(anzahl);
    }
}
