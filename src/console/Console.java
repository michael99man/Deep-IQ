package console;

import java.awt.Font;
import java.awt.TextArea;
import java.util.LinkedList;

import operators.Operator;

import main.Engine;
import main.GUI;




//FIGURE OUT HOW TO TAKE OFF SLIDERS FOR TEXTAREA

public class Console extends TextArea{

	
	private static Console instance;
	private static final long serialVersionUID = 1L;

	private static Noun noun = null;
	private static Noun.modifier modifier = null;
	private static Operator operator = null;
	private static int value = 0;
	private static Engine engine;

	private static LinkedList<String> NOUN_LIST = new LinkedList<String>();
	private static LinkedList<Noun> NOUN_OBJECT_LIST = new LinkedList<Noun>();
	
	private static final String[] MODIFIER_LIST = {"stg", "hp", "info", "delete", "turn", "untap", "tap"};
	private static Noun.modifier[] MODIFIER_OBJECT_LIST = {Noun.modifier.STG, Noun.modifier.HP, Noun.modifier.INFO, Noun.modifier.DELETE, Noun.modifier.TURN, Noun.modifier.UNTAP, Noun.modifier.TAP};
	
	private static final String[] OPERATOR_LIST = {"+","-","="};
	private static final Operator[] OPERATOR_OBJECT_LIST = {new operators.Add(), new operators.Subtract(), new operators.SetTo()};
	
	//list of keywords that end the command
	private static LinkedList<Noun.modifier> TERM_LIST = new LinkedList<Noun.modifier>();
	private static LinkedList<Noun.modifier> NON_TERM_LIST = new LinkedList<Noun.modifier>();

	public static void wipe(){
		TERM_LIST = new LinkedList<Noun.modifier>();
		NON_TERM_LIST = new LinkedList<Noun.modifier>();
		NOUN_OBJECT_LIST = new LinkedList<Noun>();
		NOUN_LIST = new LinkedList<String>();
		noun = null;
		modifier = null;
		operator = null;
		
	}
	
	public Console(){
		wipe();
		TERM_LIST.add(Noun.modifier.INFO);
		TERM_LIST.add(Noun.modifier.DELETE);
		//Check UNTAP first, as if TAP is checked first, UNTAP triggers TAP.
		TERM_LIST.add(Noun.modifier.UNTAP);
		TERM_LIST.add(Noun.modifier.TAP);
		NON_TERM_LIST.add(Noun.modifier.STG);
		NON_TERM_LIST.add(Noun.modifier.HP);
		NON_TERM_LIST.add(Noun.modifier.TURN);
		
		
		instance = this;
		setText("     Welcome to the Deep IQ Console. Input commands below.");
		add("--------------------------------------------------------------------");
		
		setFont(new Font("Andale Mono", Font.PLAIN, 12));
		setEditable(false);
		updateTokens();
	}

	
	public static void updateTokens(){
		//UPDATING STRING REFERENCES
		NOUN_LIST = new LinkedList<String>();
		NOUN_LIST.add("diq");
		NOUN_LIST.add("me");
		
		
		//UPDATING OBJECT REFERENCES
		NOUN_OBJECT_LIST = new LinkedList<Noun>();
		NOUN_OBJECT_LIST.add(Engine.diq);
		NOUN_OBJECT_LIST.add(Engine.player);
		
		int i = 1;
		for(Token t: Token.tokenList){
			NOUN_OBJECT_LIST.add(t);
			NOUN_LIST.add(("TOK" + i));
			i++;
		}
		
	}
	
	
	public static int sendCommand(String command) {
		
		if (command.replace(" ", "").equals("")){
			add("\n");
		
			return 0;
		}
		
		updateTokens();
		String response = null;
		
		final String ORIGINALCOMMAND = new String(command);
		//Check for commands first
		if (command.equalsIgnoreCase("help")){
			//print help
			System.out.println("HELP WANTED.");
			
			return 1;
		} else if (command.equalsIgnoreCase("nextturn")){
			if (Engine.state.equals(Engine.State.CREATED)){
				add("Engine not yet created.");
				return 0;
			} else {
				engine.nextTurn();
				return 1;
			}
		}



		
		for (int i = 0; i < NOUN_LIST.size(); i++){
			if (contains(command, NOUN_LIST.get(i))){
				noun = NOUN_OBJECT_LIST.get(i);
				//USEFUL THING: LinkedList.get(int index)
				
				System.out.println("Noun in statement is " + "\"" + NOUN_LIST.get(i) + "\"");
				command = command.replace(NOUN_LIST.get(i), "");
				break;
			}
		}
		
		
		
		if (noun == null){
			add("Command " + "\"" + ORIGINALCOMMAND + "\"" + " not recognized.");
			return 0;
		}
		
		
		modifier = null;
		
		for (int i = 0; i < MODIFIER_LIST.length; i++){
			if (contains(command,MODIFIER_LIST[i])){
				modifier = MODIFIER_OBJECT_LIST[i];
				System.out.println("Modifier in statement is " + "\"" + MODIFIER_LIST[i] + "\"");
				command = command.replace(MODIFIER_LIST[i], "");
			}
		}
		
		if (modifier == null){
			add("Command " + "\"" + ORIGINALCOMMAND + "\"" + " not recognized.");
			return 0;
		}
		
		LinkedList<Noun.modifier> tempList = noun.getMods();
		
		//CHECK IF MODIFIER IS LEGAL
		if (!(tempList.contains(modifier))){
			add("Command " + "\"" + ORIGINALCOMMAND + "\"" + " is an invalid command.");
			return 0;
		}
			
		
		//CHECK IF MODIFIER TERMINATES
		
		if (TERM_LIST.contains(modifier)){
			Function[] functionList = {new Function(){ public String Action(){ return noun.info();}} , new Function(){ public String Action(){ return noun.delete();}}, new Function(){ public String Action(){ return noun.untap();}}, new Function(){ public String Action(){ return noun.tap();}}   };
			
			for (int i = 0; i<TERM_LIST.size(); i++){
				if (TERM_LIST.get(i).equals(modifier)){
					System.out.println("Command executed.");
					response = functionList[i].Action();
				}
			}
			
			
		} else {
			//CHECK FOR OPERATORS & VALUES
			//CREATE FUNCTION[] WITH FUNCTIONS PASSING ON OPERATOR AND VALUE
			for (int i = 0; i < OPERATOR_LIST.length; i++){
				if (command.contains(OPERATOR_LIST[i])){
					operator = OPERATOR_OBJECT_LIST[i];
					System.out.println("Operator in statement is " + "\"" + OPERATOR_LIST[i] + "\"");
					command = command.replace(OPERATOR_LIST[i], "");
				}
			}
			
			if (operator == null){
				add("Missing operator for modifier " + "\"" + modifier.name() + "\".");
				return 0;
			}
			
			
			command = command.replace(" ", "");
			
			
			try{
				value = Integer.parseInt(command);
				System.out.println("Value in statement is " + "\"" + value + "\"");
			} catch(NumberFormatException e){
				add("Invalid value for command " + "\"" + ORIGINALCOMMAND + "\"" + ".");
				return 0;
			}
			
			
			Function[] functionList = {new Function(){ public String Action(){ return noun.stage(operator, value);}}, new Function(){ public String Action(){ return noun.hp(operator, value);}}, new Function(){ public String Action(){ return noun.turn(operator, value);}}};
		
			for (int i = 0; i<NON_TERM_LIST.size(); i++){
				if (NON_TERM_LIST.get(i).equals(modifier)){
					System.out.println("Command executed");
					response = functionList[i].Action();
				}
			}
		}
		
		GUI.updateDisplays();
		GUI.updateTokens(false);
		updateTokens();
		add(response);
		
		
		//Cleanup
		noun = null;
		modifier = null;
		operator = null;
		value = 0;
		
		return 0;
	}
	
	private static boolean contains(String command, String string) {
		
		if (command.toLowerCase().contains(string.toLowerCase())){
			return true;
		}
		return false;
	}

	interface Function{
		String Action();
	}
	
	
	private static void add(String s){
		instance.append("\n");
		instance.append(s);
	}


	public static void setEngine(Engine engine2) {
		engine = engine2;
	}
	
	public static String tabAdd(String s, String s2){
		s += "\n";
		s += "\t";
		s += s2;
		
		return s;
	}
	
	/*Keyword List (not case sensitive):
	 * 
	 * 
	 * NOUNS
	 * _____________
	 * DIQ : Deep IQ
	 * ME : The player
	 * TOKx : token  (e.g. TOK x ...)
	 * 
	 * MODIFERS
	 * _____________________
	 * STG : stage
	 * HP : Health 
	 * TURN : turn number
	 * 
	 * delete
	 * info (e.g. DIQ info or tok 1 info)
	 * tap
	 * untap
	 *
	 * OPERATOR VERBS:
	 * _____________
	 * - : subtract
	 * + : add

	 * 
	 * 
	 * 
	 * 
	 * VALUES:
	 * _____________
	 * Any number
	 * 
	 * 
	 * 
	 * Command List (not case sensitive):
	 * 
	 * help
	 * quit
	 * nextturn
	 * 
	 */
	
	
}
