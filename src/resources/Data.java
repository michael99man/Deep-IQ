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
		(new Function(){ public String Action(){ Engine.stageRoll(2); t = new Token(1, 1, Engine.requestNumber() + 1); return ("Put a 1/1 token on the battlefield (+1) and Deep IQ gets a free roll on Table II.");}}),
		(new Function(){ public String Action(){ return ("Sacrifice your best creature.");}}),
		(new Function(){ public String Action(){ Engine.spookyRoll(-2); return ("Roll on the Spooky Chart (-2)");}}),
	
		//Stage 4
		//Rolls 4-10
		(new Function(){ public String Action(){ t = new Token(4, 4, Engine.requestNumber() + 3); return ("￼￼Put a 4/4 token on the battlefield (+3)");}}),
		(new Function(){ public String Action(){ return ("Sacrifice your best creature.");}}),
		(new Function(){ public String Action(){ return ("Destroy your best artifact or enchantment.");}}),
		(new Function(){ public String Action(){ return ("Exile your best creature.");}}),
		(new Function(){ public String Action(){ Engine.player.life-=5; return ("You take 5 damage.");}}),
		(new Function(){ public String Action(){ t = new Token(2, 4, Engine.requestNumber() + 7); return ("￼￼Put a 2/4 token on the battlefield (+7)");}}),
		(new Function(){ public String Action(){ Engine.spookyRoll(0); return ("Roll on the Spooky Chart (+0)");}}),

		//Stage 5
		//Rolls 4-10
		(new Function(){ public String Action(){ t = new Token(3, 4, Engine.requestNumber() + 4); return ("￼￼Put a 3/4 token on the battlefield (+4)");}}),
		(new Function(){ public String Action(){ Engine.stageRoll(3); t = new Token(2, 2, Engine.requestNumber() + 2); return ("Put a 2/2 token on the battlefield (+2) and Deep IQ gets a free roll on Table III.");}}),
		(new Function(){ public String Action(){ return ("Destroy your best permanent.");}}),
		(new Function(){ public String Action(){ t = new Token(4, 4, Engine.requestNumber() + 1); return ("Put a 4/4 token on the battlefield (+1)");}}),
		(new Function(){ public String Action(){ Engine.diq.stage = 1; return ("Destroy all lands. Move Deep IQ to Stage 1.");}}),
		(new Function(){ public String Action(){ return ("Exile two of your best creatures.");}}),
		(new Function(){ public String Action(){ Engine.spookyRoll(2); return ("Roll on the Spooky Chart (+2)");}}),
		
		//Stage 6
		//Rolls 4-10
		(new Function(){ public String Action(){ return ("Destroy all lands, creatures and artifacts.");}}),
		(new Function(){ public String Action(){ t = new Token(4, 5, Engine.requestNumber() + 6); return ("￼￼Put a 4/5 token on the battlefield (+6)");}}),

		
		
		};
	
	
	
	interface Function{
		String Action();
	}
	
}
