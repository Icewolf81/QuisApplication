package com.future.learning.handler;

import com.future.learning.QuizFrage;

import java.util.List;

public class ErrorQuestionHandler extends QuestionLoadHandler
{
    // TODO: Füge das Error-Handling ein!
    @Override
    public List<QuizFrage> handle(int anzahl) throws QuestionLoadException
    {
        return handleNext(anzahl);
    }
}
