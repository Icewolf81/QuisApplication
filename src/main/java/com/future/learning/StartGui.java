package com.future.learning;

import com.future.learning.handler.ApiQuestionHandler;
import com.future.learning.handler.ErrorQuestionHandler;
import com.future.learning.handler.QuestionLoadException;
import com.future.learning.handler.SavedQuestionHandler;

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
			ApiQuestionHandler apiHandler = new ApiQuestionHandler();
			SavedQuestionHandler savedHandler = new SavedQuestionHandler();
			ErrorQuestionHandler errorHandler = new ErrorQuestionHandler();

			apiHandler.linkWith(savedHandler);
			savedHandler.linkWith(errorHandler);

			try
			{
				List<SingleChoiceFrage> fragen = apiHandler.handle(5);

				QuizGui quizGui = new QuizGui(fragen);
				quizGui.setVisible(true);

				dispose();
			}
			catch (QuestionLoadException ex)
			{
				JOptionPane.showMessageDialog(
						this,
						ex.getMessage()
				);
			}
		});

		pack();
	}
}