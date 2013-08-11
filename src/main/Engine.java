package main;

import java.util.LinkedList;
import java.util.Random;

import console.Player;
import console.Token;

import resources.*;
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
		GUI.updateDisplays();
		turnNumber++;

		GUI.append(divider);
		GUI.append("TURN " + turnNumber);
		GUI.append("Deep IQ has started its turn on Stage " + diq.stage + ".");

		// main phase
		int roll = roll();

		if (roll <= Data.DO_NOTHING_ROLLS[diq.stage - 1]) {
			System.out.println("Deep IQ did nothing!");
			GUI.append("Deep IQ did nothing!");
		} else {
			String text = (Data.functionList[diq.stage - 1][roll- Data.DO_NOTHING_ROLLS[diq.stage - 1] - 1].Action());
			GUI.append(text);
		}

		if (Data.ADVANCEMENT_MAX_VALUES[diq.stage - 1] >= roll) {
			diq.stage++;
			GUI.append("Deep IQ has advanced to Stage " + "\"" + diq.stage + "\".");
		}
		System.out.println("Turn over.");
		GUI.updateDisplays();
		state = State.PLAYERTURN;
	}

	public int roll() {
		int i = rand.nextInt(11);
		while (i == 0) {
			i = rand.nextInt(11);
		}
		
		//Grammar!
		if (i==8){
			GUI.append("Deep IQ has rolled an " + i);
		} else {
			GUI.append("Deep IQ has rolled a " + i);
		}
		
		return i;
	}

	public static int requestNumber() {
		return rand.nextInt(11);
	}

	public static void stageRoll(int stage) {

	}

	public static void spookyRoll(int modifier) {

	}

	public static void lose() {
		// TODO Auto-generated method stub
		
	}
	
	public static void win(){
		
	}

}
