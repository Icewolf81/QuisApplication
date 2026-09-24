package com.future.learning;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Grafische Oberfläche des Quiz. Die Klasse selbst ist ein JFrame-Fenster.
public class QuizGui extends JFrame
{
	// Layout und Panels bestimmen die grundlegende Anordnung der GUI-Elemente.
	private final BorderLayout borderLayout = new BorderLayout();
	private final JPanel hauptPanel = new JPanel(borderLayout);
	private final JPanel oberstesPanel = new JPanel(new BorderLayout());

	/*
	 * Die ButtonGroup sorgt dafür, dass immer nur ein RadioButton ausgewählt
	 * werden kann. Sie enthält die Buttons nur logisch und wird nicht angezeigt.
	 */
	private final ButtonGroup antwortGruppe = new ButtonGroup();

	// Bedienelemente und Beschriftungen des Quizfensters.
	private final JButton weiterButton = new JButton("Weiter");
	private final List<JRadioButton> antwortButtons = new ArrayList<>();
	private final JLabel punkteLabel = new JLabel("Punkte: " + Quiz.getPunktestand(), SwingConstants.CENTER);
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu menuFile = new JMenu("File");
	private final JMenuItem menuItemExit = new JMenuItem("Exit");
	private final JLabel frageLabel = new JLabel("STANDADRFRAGE?", SwingConstants.CENTER);

	// Das Quiz-Objekt verwaltet den Punktestand.
	private final Quiz quiz = new Quiz();

	// Deklaration und Initialisierung von fragen
	private final List<SingleChoiceFrage> fragen;

	// Deklaration und Initialisierung des Indexes der Liste für die Fragen
	int index = 0;

	// Beispiel-Fragen, die momentan in der grafischen Oberfläche verwendet werden.
//	SingleChoiceFrage frage1 = new SingleChoiceFrage("Was ist HTML?",
//			List.of("Eine Abkürzung",
//					"Eine Person",
//					"Eine textbasierte Sprache",
//					"Eine Firma"),
//			3);
//
//	SingleChoiceFrage frage2 = new SingleChoiceFrage("Was sind Betriebssysteme?",
//			List.of("Putzkräfte",
//					"Feuerwehr",
//					"Ärzte",
//					"Software"),
//			4);

	// Fragen aus der JSON fragen.json einlesen

	// Konstruktor: Erstellt das Fenster und fügt alle GUI-Elemente zusammen.
	public QuizGui(List<SingleChoiceFrage> fragen)
	{
		this.fragen = fragen;
		// Grundeinstellungen des Fensters.
		setTitle("Future Quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700, 500));

		// Aufbau der Menüleiste mit dem Menüpunkt zum Beenden.
		menuFile.add(menuItemExit);
		menuBar.add(menuFile);
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Frage und Punktestand werden gemeinsam im oberen Panel angezeigt.
		oberstesPanel.add(frageLabel, BorderLayout.NORTH);
		oberstesPanel.add(punkteLabel, BorderLayout.SOUTH);
		hauptPanel.add(oberstesPanel, BorderLayout.NORTH);

		// Mischt die Liste der Fragen zufällig
		Collections.shuffle(fragen);

		// Für jede Antwortmöglichkeit der aktuellen Frage wird eine Zeile im Antwortbereich angelegt.
		SingleChoiceFrage aktuelleFrage = fragen.get(index);
		JPanel antwortPanel = new JPanel(new GridLayout(aktuelleFrage.getAntworten().size(), 1));

		// Übernimmt den Text der ersten Frage in das dafür vorgesehene Label.
		frageLabel.setText(aktuelleFrage.getFrage());


		for (int i = 0; i < aktuelleFrage.getAntworten().size(); i++)
		{
			JRadioButton button = new JRadioButton(aktuelleFrage.getAntworten().get(i));

			// Die Liste ermöglicht später den Zugriff auf die einzelnen Buttons.
			antwortButtons.add(button);

			// Die Gruppe verhindert die gleichzeitige Auswahl mehrerer Antworten.
			antwortGruppe.add(button);

			// Das Panel sorgt dafür, dass der RadioButton sichtbar wird.
			antwortPanel.add(button);
		}

		// Verknüpft die Bedienelemente mit ihren auszuführenden Aktionen.
		weiterButton.addActionListener(e -> auswertenUndWeiter());
		menuItemExit.addActionListener(e -> dispose());

		// Fügt den Antwortbereich und den Weiter-Button in das Hauptpanel ein.
		hauptPanel.add(antwortPanel, BorderLayout.CENTER);
		hauptPanel.add(weiterButton, BorderLayout.SOUTH);

		// Fügt das Hauptpanel in das Fenster ein und berechnet die Fenstergröße.
		add(hauptPanel);
		pack();
	}

	// Ersetzt die bisher angezeigte Frage und die Antworttexte durch Frage 2.
	private void zeigeNaechsteFrage()
	{
		if /*(index < fragen.size() - 1)*/ (index < 4) // Nur fünf Fragen für Bonus 1
		{
			index++;
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Quiz beendet! Du hast " + Quiz.getPunktestand() + " Punkte erreicht.");
			dispose(); // schließt das Fenster und gibt dessen Ressourcen frei
			return; // wird noch benötigt damit die Methode sauber beendet wird.
		}

		SingleChoiceFrage aktuelleFrage = fragen.get(index);

		frageLabel.setText(aktuelleFrage.getFrage());

		for (int i = 0; i < aktuelleFrage.getAntworten().size(); i++)
		{
			antwortButtons.get(i).setText(aktuelleFrage.getAntworten().get(i));
		}
	}

	/*
	 * Sucht den ausgewählten RadioButton.
	 * Die Rückgabe beginnt bei 1, weil auch die Antwortnummern bei 1 beginnen.
	 * Der Wert 0 bedeutet, dass noch keine Antwort ausgewählt wurde.
	 */
	private int getAusgewaehlteAntwort()
	{
		for (int i = 0; i < antwortButtons.size(); i++)
		{
			if (antwortButtons.get(i).isSelected())
			{
				return i + 1;
			}
		}

		return 0;
	}

	// Wertet die aktuelle Auswahl aus, aktualisiert die Punkte und zeigt Frage 2.
	private void auswertenUndWeiter()
	{
		int auswahl = getAusgewaehlteAntwort();

		// Ohne Auswahl wird eine Meldung angezeigt und die Methode beendet.
		if (auswahl == 0)
		{
			JOptionPane.showMessageDialog(this, "Bitte wähle zuerst eine Antwort aus.");
			return;
		}

		// Nur bei einer richtigen Antwort wird der Punktestand erhöht.
		if (fragen.get(index).isRichtig(auswahl))
		{
			quiz.addPunkte();
		}

		// Das Label muss nach einer Änderung des Punktestands neu gesetzt werden.
		punkteLabel.setText("Punkte: " + Quiz.getPunktestand());

		// Entfernt die Markierung der bisher ausgewählten Antwort.
		antwortGruppe.clearSelection();

		zeigeNaechsteFrage();
	}
}
