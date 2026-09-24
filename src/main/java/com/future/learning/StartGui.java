package com.future.learning;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StartGui extends JFrame
{
	private final JButton eigeneFragenButton = new JButton("Deutsch/Offline");
	private final JButton triviaFragenButton = new JButton("Englisch/Online");

	public StartGui()
	{
		setTitle("Future Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 250));

		JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));

		buttonPanel.add(eigeneFragenButton);
		buttonPanel.add(triviaFragenButton);

		add(buttonPanel);

		eigeneFragenButton.addActionListener(e ->
		{
			List<SingleChoiceFrage> fragen = FragenLoader.ladeFragen();

			QuizGui quizGui = new QuizGui(fragen);
			quizGui.setVisible(true);

			dispose();
		});

		triviaFragenButton.addActionListener(e ->
		{
			TriviaDownloader.ladeFragenHerunter();

			List<SingleChoiceFrage> fragen = TriviaFragenLoader.ladeFragen();

			QuizGui quizGui = new QuizGui(fragen);
			quizGui.setVisible(true);

			dispose();
		});

		pack();
	}
}