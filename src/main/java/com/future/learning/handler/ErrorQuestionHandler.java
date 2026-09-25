package com.future.learning.handler;

import com.future.learning.SingleChoiceFrage;

import java.util.List;

public class ErrorQuestionHandler extends QuestionLoadHandler
{
    @Override
    public List<SingleChoiceFrage> handle(int anzahl)
    {
        throw new QuestionLoadException(
                "Fragen konnten weder über die API noch aus dem Cache geladen werden."
        );
    }
}