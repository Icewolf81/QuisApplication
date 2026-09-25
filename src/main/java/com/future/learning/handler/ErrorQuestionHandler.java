package com.future.learning.handler;

import com.future.learning.QuizFrage;

import java.util.List;

public class ErrorQuestionHandler extends QuestionLoadHandler
{
    // TODO: Füge das Error-Handling ein!
    @Override
    public List<QuizFrage> handle(int anzahl) throws QuestionLoadException
    {
        try
        {
            System.out.println("Ich habe das Error-Handling versucht!");
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
