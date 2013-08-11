package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import console.Console;
import console.Token;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static Engine engine = null;
	private static JFrame frame;

	@SuppressWarnings("unused")
	private static Console console;
	static TextArea textArea;

	public static GUI instance;
	private static JLabel stageLabel;
	private static JLabel lifeLabel;
	private static JLabel enemyLifeLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GUI();
					frame.setVisible(true);
					WelcomeScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */

	public static void gameScreen() {
		frame.dispose();

		frame = new GUI();
		frame.setVisible(true);
		frame.setTitle("Deep IQ");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 800);
		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		frame.getContentPane().setLayout(null);

		stageLabel = new JLabel();
		lifeLabel = new JLabel();
		enemyLifeLabel = new JLabel();

		stageLabel.setBounds(50, 30, 85, 85);
		lifeLabel.setBounds(150, 30, 85, 85);
		enemyLifeLabel.setBounds(250, 30, 85, 85);

		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY), "Stage");

		title.setTitleFont((new Font("AppleGothic", Font.ITALIC, 16)));
		title.setTitleJustification(TitledBorder.LEFT);

		TitledBorder title2 = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY), "Your Life");

		title2.setTitleFont((new Font("AppleGothic", Font.ITALIC, 16)));
		title2.setTitleJustification(TitledBorder.LEFT);

		TitledBorder title3 = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY), "DIQ Life");

		title3.setTitleFont((new Font("AppleGothic", Font.ITALIC, 16)));
		title3.setTitleJustification(TitledBorder.LEFT);

		stageLabel.setBorder(title);
		lifeLabel.setBorder(title2);
		enemyLifeLabel.setBorder(title3);

		TitledBorder title4 = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY), "Output");

		title4.setTitleFont((new Font("AppleGothic", Font.ITALIC, 25)));
		title4.setTitleJustification(TitledBorder.LEFT);

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(50, 390, 800, 350);
		textArea.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		textArea.setText("Welcome to Michael Man's version of Deep IQ.");
		append("Check this area for game updates.");

		final JButton nextTurn;
		nextTurn = new JButton("Begin");
		nextTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				engine.nextTurn();
				nextTurn.setText("Next Turn");
			}
		});
		nextTurn.setBounds(50, 300, 200, 85);

		Console console = new Console();
		console.setBounds(350, 30, 500, 300);

		final JTextField inputField = new JTextField();
		inputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					System.out.println("Command sent: " + inputField.getText());
					Console.sendCommand(inputField.getText());
					inputField.setText("");
				}
			}
		});
		
		inputField.setBounds(347,333,506,35);
		inputField.setText("help");
		
		contentPane.add(inputField);
		contentPane.add(console);
		contentPane.add(nextTurn);
		contentPane.add(textArea);
		contentPane.add(stageLabel);
		contentPane.add(lifeLabel);
		contentPane.add(enemyLifeLabel);
	}

	public GUI() {
		instance = this;
	}

	public static void append(String s) {
		textArea.append("\n");
		textArea.append(s);
	}

	public static void WelcomeScreen() {
		frame.setTitle("Deep IQ");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		frame.setContentPane(contentPane);
		frame.getContentPane().setLayout(null);

		JLabel welcLabel = new JLabel("Deep IQ awaits your challenge...");
		welcLabel.setFont(new Font("Copperplate", Font.ITALIC, 50));
		welcLabel.setBounds(230, 110, 845, 50);
		contentPane.add(welcLabel);

		JButton startGame = new JButton("Start Game");
		startGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (engine == null) {
					gameScreen();
					engine = new Engine(instance);									
				} else {
					System.out.println("Engine already exists");
				}
			}
		});

		startGame.setBounds(292, 267, 667, 325);
		contentPane.add(startGame);

		// Setup
		Token.setParent(instance);
	}

	public void addToken(Token token) {
		// TODO Auto-generated method stub

	}

	public static void updateDisplays() {
		stageLabel.setText(String.valueOf(Engine.diq.stage));
		lifeLabel.setText(String.valueOf(Engine.player.life));
		enemyLifeLabel.setText(String.valueOf(Engine.diq.life));
		
	}
}
