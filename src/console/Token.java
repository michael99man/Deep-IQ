package console;

import java.util.LinkedList;
import java.util.List;

import operators.Operator;

import main.GUI;

public class Token implements Noun {

	
	public static LinkedList<Token> tokenList = new LinkedList<Token>();
	public TYPE type;
	public int power;
	public int toughness;
	public String desc;
	public static GUI parent = null;
	
	public int tokenAmount;
	
	public static void setParent(GUI gui){
		parent = gui;
	}
			
	public Token(TYPE type, int p, int t, String describer){
		tokenAmount++;
		this.type = type;
		power = p;
		toughness = t;
		desc = describer;
		
		tokenList.add(this);
		parent.addToken(this);
	}
	
	
	public static enum TYPE {
		Creature, Enchantment;
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
