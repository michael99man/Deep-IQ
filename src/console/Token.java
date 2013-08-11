package console;

import java.util.LinkedList;

import operators.Operator;

import main.Engine;
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
		
		processAbilities(roll);
		
		type = TYPE.Creature;
		power = p;
		toughness = t;
		
		tokenList.add(this);
		parent.addToken(this);
	}
	
	private void processAbilities(int roll){
		//Sigh... Using if loops is the simplest way to do this.
		
		if (roll <= 1){
			System.out.println("No extra abilities.");
		} else if (roll == 2){
			abilityList.add(staticAbilities.First_Strike);
		} else if (roll == 3){
			toughness+=3;
			abilityList.add(staticAbilities.Defender);
		} else if (roll == 4){
			abilityList.add(staticAbilities.Flying);
		} else if (roll == 5){
			//Add a random Pro
			staticAbilities[] tempArray = {staticAbilities.ProRed, staticAbilities.ProBlue, staticAbilities.ProBlack, staticAbilities.ProWhite,staticAbilities.ProGreen};
			int i = Engine.requestNumber();
			
			if (i % 2 == 1){
				abilityList.add(tempArray[((i-1)/2 + 1)]);
			} else {
				abilityList.add(tempArray[i/2]);
			}
		} else if (roll == 6){
			abilityList.add(staticAbilities.Deathtouch);
			abilityList.add(staticAbilities.Defender);
		} else if (roll == 7){
			power+=2;
			abilityList.add(staticAbilities.Flying);
		} else if (roll == 8){
			power+=1;
			toughness+=2;
			abilityList.add(staticAbilities.Lifelink);
		} else if (roll == 9){
			power+=2;
			abilityList.add(staticAbilities.Trample);
			abilityList.add(staticAbilities.Haste);
		} else if (roll == 10){
			//Add a random Pro
			staticAbilities[] tempArray = {staticAbilities.ProRed, staticAbilities.ProBlue, staticAbilities.ProBlack, staticAbilities.ProWhite,staticAbilities.ProGreen};
			int i = Engine.requestNumber();
			
			if (i % 2 == 1){
				abilityList.add(tempArray[((i-1)/2 + 1)]);
			} else {
				abilityList.add(tempArray[i/2]);
			}
			
			abilityList.add(staticAbilities.Vigilance);
			abilityList.add(staticAbilities.Lifelink);
		} else if (roll == 11){
			power+=2;
			toughness +=2;
			abilityList.add(staticAbilities.Shroud);
		} else if (roll == 12){
			abilityList.add(staticAbilities.Annihilator1);
			abilityList.add(staticAbilities.First_Strike);
		} else if (roll == 13){
			//Angel of Death
			abilityList.add(staticAbilities.Lifelink);
			abilityList.add(staticAbilities.Deathtouch);
			abilityList.add(staticAbilities.Flying);
		} else if (roll == 14){
			power+=1;
			toughness+=1;
			abilityList.add(staticAbilities.Annihilator2);
		} else if (roll == 15){
			power += 2;
			toughness += 2;
			abilityList.add(staticAbilities.Lifelink);
			abilityList.add(staticAbilities.Flying);
			abilityList.add(staticAbilities.First_Strike);
		} else if (roll >= 16){
			//Welcome Progenitus to the world.
			abilityList.add(staticAbilities.ProBlack);
			abilityList.add(staticAbilities.ProRed);
			abilityList.add(staticAbilities.ProGreen);
			abilityList.add(staticAbilities.ProWhite);
			abilityList.add(staticAbilities.ProBlue);
		}
		
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
		Defender,
		Haste,
		Trample,
		Vigilance,
		Shroud,
		
		ProBlack,
		ProRed,
		ProBlue,
		ProGreen,
		ProWhite,
		
		Annihilator1,
		Annihilator2
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
