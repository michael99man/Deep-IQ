package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import console.Console;
import console.Token;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static Engine engine = null;
	private static JFrame frame;

	@SuppressWarnings("unused")
	private static Console console;
	static TextArea textArea;

	public static  JButton nextTurn;
	public static GUI instance;
	private static JLabel stageLabel;
	private static JLabel lifeLabel;
	private static JLabel enemyLifeLabel;

	public static JTabbedPane tabbedPane;

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

		Font f = new Font("Copperplate", Font.PLAIN, 40);
		stageLabel.setFont(f);
		lifeLabel.setFont(f);
		enemyLifeLabel.setFont(f);

		stageLabel.setBounds(50, 30, 85, 85);
		lifeLabel.setBounds(150, 30, 85, 85);
		enemyLifeLabel.setBounds(250, 30, 85, 85);

		TitledBorder title = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY), "Stage");

		Font font = new Font("AppleGothic", Font.BOLD, 16);
		title.setTitleFont(font);
		title.setTitleJustification(TitledBorder.LEFT);

		TitledBorder title2 = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY), "Your Life");

		title2.setTitleFont(font);
		title2.setTitleJustification(TitledBorder.LEFT);

		TitledBorder title3 = BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY), "DIQ Life");

		title3.setTitleFont(font);
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

		
		nextTurn = new JButton("Begin");
		nextTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (Engine.state.equals(Engine.State.FINISHED)){
					//Starts a new game
					gameScreen();
					engine = new Engine(instance);
					Console.setEngine(engine);
					//Cleanup
					Token.tokenList = new LinkedList<Token>();
				} else if (Engine.state.equals(Engine.State.PLAYERTURN) || Engine.state.equals(Engine.State.CREATED)){
					engine.nextTurn();
					nextTurn.setText("Next Turn");
				}
				
			}
		});
		nextTurn.setBounds(50, 300, 290, 85);
		nextTurn.setFont(new Font("AppleGothic", Font.BOLD, 40));
		nextTurn.setFocusable(false);

		Console console = new Console();
		console.setBounds(350, 30, 500, 300);

		final JTextField inputField = new JTextField();
		inputField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("Command sent: " + inputField.getText());
					Console.sendCommand(inputField.getText());
					inputField.setText("");
				}
			}
		});

		inputField.setBounds(347, 333, 506, 35);
		inputField.setText("help");

		contentPane.add(inputField);
		contentPane.add(console);
		contentPane.add(nextTurn);
		contentPane.add(textArea);
		contentPane.add(stageLabel);
		contentPane.add(lifeLabel);
		contentPane.add(enemyLifeLabel);

		// TOKENS

		/*
		 * JPanel tokenPanel = new JPanel(); tokenPanel.setPreferredSize(new
		 * Dimension(290,165)); tokenPanel.setBackground(Color.BLUE);
		 * tokenPanel.setBounds(50,125,290,165);
		 */
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(50, 125, 290, 165);

		contentPane.add(tabbedPane);
	}

	public static void addTab(int num, Token t, boolean refocus) {

		TokenHolder pane = new TokenHolder(t);
		pane.setLayout(null);

		JLabel consoleName = new JLabel("Console name: TOK" + num);
		consoleName.setBounds(5, 0, 170, 18);

		JLabel pt = new JLabel("P/T: " + t.power + "/" + t.toughness);
		pt.setBounds(5, 18, 100, 18);
		
		JLabel tapped = new JLabel("Tapped: " + t.tapped);
		tapped.setBounds(5, 36, 150, 18);

		Font font = new Font("AppleGothic", Font.BOLD, 14);
		pt.setFont(font);
		consoleName.setFont(font);
		tapped.setFont(font);

		int height = 48;

		Font aFont = new Font("AppleGothic", Font.PLAIN, 13);
		for (Token.staticAbilities sa : t.abilityList) {
			String str = sa.name();

			str = str.replace("_", " ");

			JLabel label = new JLabel(str);
			label.setFont(aFont);

			label.setBounds(5, height, 200, 20);
			pane.add(label);
			height += 15;
		}

		pane.add(consoleName);
		pane.add(pt);
		pane.add(tapped);

		tabbedPane.addTab("Token " + num, null, pane, "TOK" + num);

		if (refocus) {
			tabbedPane.setSelectedIndex(num - 1);
		}
		instance.validate();
		/*
		 * int[] keyArray = {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3,
		 * KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_7,
		 * KeyEvent.VK_8,KeyEvent.VK_9};
		 * 
		 * if (num<10){ tabbedPane.setMnemonicAt(num-1, keyArray[num-1]); }
		 * 
		 * DOESN'T WORK
		 */
	}

	public GUI() {
		instance = this;
	}

	public static void append(String s) {
		textArea.append("\n");
		textArea.append(s);
	}

	public static void tabAppend(String s) {
		textArea.append("\n");
		textArea.append("\t");
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
					Console.setEngine(engine);
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

	public static void updateDisplays() {
		stageLabel.setText(" " + String.valueOf(Engine.diq.stage));
		lifeLabel.setText(" " + String.valueOf(Engine.player.life));

		if (Engine.player.life <= 0) {
			Engine.lose();
		} else if (Engine.diq.life <= 0) {
			Engine.win();
		}
		enemyLifeLabel.setText(" " + String.valueOf(Engine.diq.life));

	}

	public static void updateTokens(boolean refocus) {
		// UPDATE TOKEN DISPLAY HERE
		tabbedPane.removeAll();
		contentPane.remove(tabbedPane);
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(50, 125, 290, 165);

		contentPane.add(tabbedPane);

		int i = 1;
		for (Token t : Token.tokenList) {
			addTab((i), t, refocus);
			t.consoleName = ("TOK" + i);
			i++;
		}
		instance.repaint();
	}

}

class TokenHolder extends JPanel {

	private static final long serialVersionUID = 1L;
	Token token;

	public TokenHolder(Token t) {
		token = t;
	}

}