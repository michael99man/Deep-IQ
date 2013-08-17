package main;

import java.util.LinkedList;
import java.util.Random;

import console.Player;
import console.Token;

import resources.Data;

public class Engine {

	public static State state;
	public static String divider = "------------------------------------------------------------------------------------------------";

	public static Player player;
	public static Player diq;
	public LinkedList<Token> tokenList = new LinkedList<Token>();
	private static Random rand = new Random();
	public int turnNumber = 0;
	public GUI parent;

	public static boolean boost;
	
	public enum State {
		CREATED, STARTED, PLAYERTURN, DIQTURN, FINISHED
	}

	public Engine(GUI gui) {
		parent = gui;
		System.out.println("Engine created.");
		player = new Player(Player.Type.PLAYER);
		diq = new Player(Player.Type.DIQ);
		state = State.CREATED;
		GUI.updateDisplays();
		GUI.append(divider);
	}

	public void nextTurn() {
		state = State.DIQTURN;
		Token.untapAll();
		GUI.updateDisplays();
		turnNumber++;

		GUI.append(divider);
		GUI.append("TURN " + turnNumber);
		GUI.append("Deep IQ has started its turn.");

		// main phase
		int roll = roll("Stage " + diq.stage + ".");

		if (roll <= Data.DO_NOTHING_ROLLS[diq.stage - 1]) {
			GUI.append("Deep IQ did nothing!");
			
			
			//REDO CODE
			boolean redo = false;
			for (Token t: Token.tokenList){
					if (t.desc
							.equalsIgnoreCase("Reroll the first \"Do nothing\" result of every turn.")){
					redo = true;
				}
			}
			
			if (redo){
				GUI.append("The Artifact allows Deep IQ to reroll!");
				roll = roll("Stage " + diq.stage + ".");
				
				if (roll <= Data.DO_NOTHING_ROLLS[diq.stage - 1]) {
					GUI.append("Deep IQ did nothing again!");
				} else {
					Data.functionList[diq.stage - 1][roll- Data.DO_NOTHING_ROLLS[diq.stage - 1] - 1].Action();
				}
			}
		
		} else {

			Data.functionList[diq.stage - 1][roll
					- Data.DO_NOTHING_ROLLS[diq.stage - 1] - 1].Action();

		}

		if (Data.ADVANCEMENT_MAX_VALUES[diq.stage - 1] >= roll) {
			diq.stage++;
			GUI.append("Deep IQ has advanced to Stage " + diq.stage
					+ ".");
		}
		state = State.PLAYERTURN;
		GUI.updateDisplays();
	}

	public static int roll(String on) {
		int i = rand.nextInt(11);
		while (i == 0) {
			i = rand.nextInt(11);
		}

		if (boost && i<10){
			i++;
		}
		
		// Grammar!
		if (i == 8) {
			GUI.append("Deep IQ has rolled an " + i + " on " + on);
		} else {
			GUI.append("Deep IQ has rolled a " + i + " on " + on);
		}

		return i;
	}

	public static int requestNumber() {	
		int i = rand.nextInt(11);
		while (i == 0) {
			i = rand.nextInt(11);
		}
		if (boost){
			i++;
		}
		return i;
	}

	public static void stageRoll(int stage) {
		int roll = roll("Stage " + stage + ".");

		if (roll <= Data.DO_NOTHING_ROLLS[stage - 1]) {
			GUI.append("Deep IQ did nothing!");
		} else {
			Data.functionList[stage - 1][roll
					- Data.DO_NOTHING_ROLLS[stage - 1] - 1].Action();
		}
	}

	public static void spookyRoll(int modifier) {
		Data.spooky(modifier);
	}

	public static void lose() {
		state = State.FINISHED;
		GUI.append(divider);
		GUI.append(divider);
		GUI.append("DEEP IQ HAS TRIUMPHED");
		GUI.append("GAME OVER");

		GUI.nextTurn.setText("Try again?");
	}

	public static void win() {
		state = State.FINISHED;
		GUI.append(divider);
		GUI.append(divider);
		GUI.append("CONGRATULATIONS!");
		GUI.append("YOU HAVE DEFEATED DEEP IQ");
		GUI.append("GAME OVER");

		GUI.nextTurn.setText("Again?");
	}

}
