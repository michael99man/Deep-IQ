package resources;

import main.Engine;
import console.Token;


public final class Data {

	public static Function[][] levelList;
	public static final int[] ADVANCEMENT_MAX_VALUES = {9,8,7,6,5,0};
	
	//Newly created Token; Made to stop annoying warnings, as Token is added to Engine in constructor. 
	private static Token t;
	
	public static final Function[] functionList =
		//Stage 1
		//Rolls 8-10
		{ (new Function(){ public String Action(){ return ("Sacrifice your best creature.)");}}), 
		(new Function(){ public String Action(){ t = new Token(1, 1, Engine.requestNumber() - 4);  return ("Put a 1/1 token on the battlefield (-4)");}}),
		(new Function(){ public String Action(){ t = new Token(1, 1, Engine.requestNumber() - 4);  return ("Put a 1/1 token on the battlefield (-4)");}}),
		
		
		//Stage 2
		//Rolls 5-10
		(new Function(){ public String Action(){ t = new Token(2, 2, Engine.requestNumber()); return ("Put a 2/2 token on the battlefield (+0)");}}),
		(new Function(){ public String Action(){ t = new Token(2, 2, Engine.requestNumber()); return ("Put a 2/2 token on the battlefield (+0)");}}),
		(new Function(){ public String Action(){ t = new Token(2, 2, Engine.requestNumber()); return ("Put a 2/2 token on the battlefield (+0)");}}),
		(new Function(){ public String Action(){Engine.diq.stage = 4; return ("Move Deep IQ up to Table IV");}}),
		(new Function(){ public String Action(){ return ("Exile your best creature.");}}),
		(new Function(){ public String Action(){ return ("Exile your best creature.");}}),
		
		//Stage 3
		//Rolls 4-10
		(new Function(){ public String Action(){ t = new Token(2, 2, Engine.requestNumber() + 2); return ("Put a 2/2 token on the battlefield (+2)");}}),
		(new Function(){ public String Action(){ t = new Token(2, 1, Engine.requestNumber() + 4); return ("Put a 2/1 token on the battlefield (+4)");}}),
		(new Function(){ public String Action(){ return ("Destroy your best land.");}}),
		(new Function(){ public String Action(){ Engine.diq.stage = 5; t = new Token(1, 1, Engine.requestNumber()); return ("￼￼Move Deep IQ up to Table V and put a 1/1 token on battlefield (+0)");}}),
		(new Function(){ public String Action(){ Engine.manualRoll(Engine.RollType.Stage, 2); t = new Token(1, 1, Engine.requestNumber() + 1); return ("Put a 1/1 token on the battlefield (+1) and Deep IQ gets a free roll on Table II.");}}),
		(new Function(){ public String Action(){ return ("Sacrifice your best creature.");}}),

	
		};
	
	
	
	interface Function{
		String Action();
	}
	
}
