package com.future.learning.handler;

import com.future.learning.QuizFrage;

import java.util.List;

public class ApiQuestionHandler extends QuestionLoadHandler
{
    // TODO: Füge die API-Anfrage ein!
    @Override
    public List<QuizFrage> handle(int anzahl) throws QuestionLoadException
    {
        try
        {
            System.out.println("Ich habe API versucht!");
            if (true == false)
            {
                return List.of();
            }
        } catch (IllegalStateException e)
        {
            return List.of();
        }
        return handleNext(anzahl);
    }
}
