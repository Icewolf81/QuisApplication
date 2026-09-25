package com.future.learning.handler;

import com.future.learning.SingleChoiceFrage;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SavedQuestionHandler extends QuestionLoadHandler
{
    private static final Path DATEI =
            Path.of("src", "main", "resources", "fragen2.json");

    @Override
    public List<SingleChoiceFrage> handle(int anzahl)
    {
        try
        {
            String json = Files.readString(
                    DATEI,
                    StandardCharsets.UTF_8
            );

            return parseFragen(json);
        }
        catch (Exception e)
        {
            return handleNext(anzahl);
        }
    }
}