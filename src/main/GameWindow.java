package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class GameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PlayerChoice[] choices = PlayerChoice.values();
	private PlayerChoice playerChoice = null, computerChoice = null;
	private int playerScore = 0, computerScore = 0, turns = 0;
	private Random randomEngine;

	private JLabel lblPlayerChoice, lblComputerChoice, lblScore;

	public GameWindow() {
		randomEngine = new Random();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 114, 414, 2);
		contentPane.add(separator);

		lblPlayerChoice = new JLabel(playerChoice == null ? "You: " : "You: " + playerChoice);
		lblPlayerChoice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPlayerChoice.setBounds(10, 52, 139, 23);
		contentPane.add(lblPlayerChoice);

		lblComputerChoice = new JLabel(computerChoice == null ? "Computer: " : "Computer: " + computerChoice);
		lblComputerChoice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComputerChoice.setBounds(212, 52, 212, 23);
		contentPane.add(lblComputerChoice);

		JButton btnPaper = new JButton("Paper");
		btnPaper.setBounds(173, 127, 89, 23);

		btnPaper.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerChoice = PlayerChoice.Paper;
				computerChoice = choices[randomEngine.nextInt(3)];

				calculateScore();
				setLabels();
			}
		});
		contentPane.add(btnPaper);

		JButton btnRock = new JButton("Rock");
		btnRock.setBounds(10, 127, 89, 23);

		btnRock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerChoice = PlayerChoice.Rock;
				computerChoice = choices[randomEngine.nextInt(3)];

				calculateScore();
				setLabels();
			}
		});

		contentPane.add(btnRock);

		JButton btnScissor = new JButton("Scissor");
		btnScissor.setBounds(335, 127, 89, 23);

		btnScissor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playerChoice = PlayerChoice.Scissor;
				computerChoice = choices[randomEngine.nextInt(3)];

				calculateScore();
				setLabels();
			}
		});
		contentPane.add(btnScissor);

		lblScore = new JLabel("Score: Player: " + playerScore + " Computer: " + computerScore + " Turns: " + turns);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblScore.setBounds(10, 86, 414, 23);
		contentPane.add(lblScore);

		JLabel lblTitle = new JLabel("Rock, Paper and Scissors Game");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(10, 11, 414, 23);
		contentPane.add(lblTitle);

		setResizable(false);
	}

	private void calculateScore() {
		switch (GameLogic.getOutcome(playerChoice, computerChoice)) {
		case Win:
			playerScore++;
			break;
		case Lose:
			computerScore++;
			break;
		default:
			break;
		}

		turns++;
	}

	private void setLabels() {
		lblPlayerChoice.setText(playerChoice == null ? "You: " : "You: " + playerChoice);
		lblComputerChoice.setText(computerChoice == null ? "Computer: " : "Computer: " + computerChoice);

		lblScore.setText("Score: Player: " + playerScore + " Computer: " + computerScore + " Turns: " + turns);
	}
}
