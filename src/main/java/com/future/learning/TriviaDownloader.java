package com.future.learning;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TriviaDownloader
{
	private static final String API_URL =
			"https://opentdb.com/api.php?amount=10&type=multiple";

	private static final Path ZIELDATEI =
			Path.of("src", "main", "resources", "fragen2.json");

	public static void ladeFragenHerunter()
	{
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(API_URL))
				.GET()
				.build();

		try
		{
			HttpResponse<String> response = client.send(
					request,
					HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
			);

			if (response.statusCode() != 200)
			{
				throw new IllegalStateException(
						"HTTP-Fehler: " + response.statusCode()
				);
			}

			Files.writeString(
					ZIELDATEI,
					response.body(),
					StandardCharsets.UTF_8,
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING
			);
		}
		catch (IOException | InterruptedException e)
		{
			throw new IllegalStateException(
					"Die Trivia-Fragen konnten nicht heruntergeladen werden.",
					e
			);
		}
	}
}