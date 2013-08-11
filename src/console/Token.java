package console;

import java.util.LinkedList;

import operators.Operator;

import main.GUI;

public class Token implements Noun {

	
	public static LinkedList<Token> tokenList = new LinkedList<Token>();
	public TYPE type;
	public int power;
	public int toughness;
	public String desc;
	public static GUI parent = null;
	public LinkedList<staticAbilities> abilityList = new LinkedList<staticAbilities>();
	
	public int tokenAmount;
	
	public static void setParent(GUI gui){
		parent = gui;
	}
			
	//Creature
	public Token(int p, int t, int roll){
		tokenAmount++;
		
		//process roll
		
		type = TYPE.Creature;
		power = p;
		toughness = t;
		
		tokenList.add(this);
		parent.addToken(this);
	}
	
	
	//Other
	public Token(TYPE type, String describer){
		tokenAmount++;
		this.type = type;
		desc = describer;
		
		tokenList.add(this);
		parent.addToken(this);
	}
	
	public static enum TYPE {
		Creature, Enchantment;
	}


	public static enum staticAbilities {
		Lifelink,
		Deathtouch,
		Flying,
		First_Strike,
		Regeneration,
		Defender,
		Haste,
		Trample,
		Vigilance,
		Shroud,
		
		ProBlack,
		ProRed,
		ProBlue,
		ProGreen,
		ProWhite
	}

	@Override
	public LinkedList<modifier> getMods() {
		LinkedList<modifier> list = new LinkedList<modifier>();
		list.add(modifier.INFO);
		list.add(modifier.DELETE);
		list.add(modifier.HP);
		
		return list;
	}

	
	

	@Override
	public String info() {
		return "yo homie";
	}

	@Override
	public String stage(Operator o, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hp(Operator o, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String turn(Operator o, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
