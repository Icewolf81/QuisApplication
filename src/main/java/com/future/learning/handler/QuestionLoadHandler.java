package com.future.learning.handler;

import com.future.learning.QuizFrage;

import java.util.List;

public abstract class QuestionLoadHandler
{
    private QuestionLoadHandler nextHandler;

    public void linkWith(QuestionLoadHandler nextHandler)
    {
        this.nextHandler = nextHandler;
    }

    public abstract List<QuizFrage> handle(int anzahl) throws QuestionLoadException;

    public List<QuizFrage> handleNext(int anzahl) throws QuestionLoadException
    {
        if (nextHandler == null)
        {
            throw new QuestionLoadException("CoR findet keinen weiteren Handler!");
        }
        return nextHandler.handle(anzahl);
    }
}
