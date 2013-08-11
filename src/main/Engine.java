package main;

import java.util.LinkedList;
import java.util.Random;

import console.Player;
import console.Token;

import resources.*;

public class Engine {

	public static State state;
	public static String divider = "------------------------------------------------------------------------------------------------";
	
	public static Player player;
	public static Player diq;
	public LinkedList<Token> tokenList = new LinkedList<Token>();
	private static Random rand = new Random();
	public int turnNumber = 0;
	public GUI parent;
	
	
	
	public enum State{
		CREATED,
		STARTED,
		PLAYERTURN,
		DIQTURN,
		FINISHED
	}
	
	public Engine(GUI gui) {
		parent = gui;
		System.out.println("Engine created");
		player = new Player(Player.Type.PLAYER);
		diq = new Player(Player.Type.DIQ);
		GUI.updateDisplays();
		GUI.append(divider);
	}

	public void nextTurn() {
		state = State.DIQTURN;
		GUI.updateDisplays();
		turnNumber++;
		
		GUI.append(divider);
		GUI.append("TURN " + turnNumber);
		GUI.append("Deep IQ has started its turn on Stage " + diq.stage);
		
		//main phase
		int roll = roll();
		GUI.append("Deep IQ has rolled a " + roll);
		

		
		if (Data.ADVANCEMENT_MAX_VALUES[diq.stage-1] >= roll){
			diq.stage++;
			GUI.append("Deep IQ has advanced to stage " + "\"" + diq.stage + "\".");
		}
		System.out.println("Turn over");
		GUI.updateDisplays();
		state = State.PLAYERTURN;
	}

	public int roll() {
		int i = rand.nextInt(11);
		while (i==0){
			i=rand.nextInt(11);
		}
		return i;
	}

	public static int requestNumber(){
		return rand.nextInt();
	}
	
	public enum RollType{
		Spooky,
		Stage
	}
	
	public static void manualRoll(RollType rt, int stage){
		//ROLL AGAIN HERE
	}
	
	
	
}
