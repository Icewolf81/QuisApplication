package com.future.learning.handler;

import com.future.learning.SingleChoiceFrage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ApiQuestionHandler extends QuestionLoadHandler
{
    private static final String API_URL =
            "https://opentdb.com/api.php?amount=";

    private static final Path ZIELDATEI =
            Path.of("src", "main", "resources", "fragen2.json");

    @Override
    public List<SingleChoiceFrage> handle(int anzahl)
    {
        try
        {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + anzahl + "&type=multiple"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
            );

            if (response.statusCode() != 200)
            {
                return handleNext(anzahl);
            }

            // Erfolgreiche API-Antwort als Cache speichern.
            Files.writeString(
                    ZIELDATEI,
                    response.body(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

            return parseFragen(response.body());
        }
        catch (Exception e)
        {
            return handleNext(anzahl);
        }
    }
}