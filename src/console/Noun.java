package console;

import java.util.LinkedList;

import operators.Operator;

public interface Noun {
	LinkedList<modifier> getMods();
	//In console, check if returned list includes modifier
	
	//A modifier is something that can directly follow a noun
	public enum modifier{
		STG,
		HP,
		TURN,
		DELETE,
		INFO, 
		TAP,
		UNTAP
	}
	
	
	//Pass back response
	String stage(Operator o, int b);
	String hp(Operator o, int b);
	String turn(Operator o, int b);
	String delete();
	String info();
	String tap();
	String untap();
}

