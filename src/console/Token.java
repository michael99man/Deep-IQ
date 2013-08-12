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
	public String desc = "";
	public static GUI parent = null;
	public LinkedList<staticAbilities> abilityList = new LinkedList<staticAbilities>();
	public String consoleName;
	public static int tokenAmount = 0;

	
	public boolean tapped = false;
	
	
	public static void untapAll(){
		for (Token t: tokenList){
			t.tapped = false;
		}
	}
	
	public static void setParent(GUI gui) {
		parent = gui;
	}

	// Creature
	public Token(int p, int t, int roll) {
		tokenAmount++;
		consoleName = "TOK" + tokenAmount;
		type = TYPE.Creature;
		power = p;
		toughness = t;

		processAbilities(roll);
		tokenList.add(this);
		GUI.updateTokens(true);

		// Update Console List
		Console.updateTokens();
	}

	public void tabAppend(String s) {

		desc += "\t";
		desc += s;
		desc += "\n";
	}

	public void processAbilities(int roll) {
		// Sigh... Using if loops is the simplest way to do this.
		if (roll == 8 || roll == 11) {
			tabAppend("Deep IQ has rolled an " + roll + " on the Token Chart");
		} else {
			tabAppend("Deep IQ has rolled a " + roll + " on the Token Chart");
		}

		// OUTPUT: tab
		if (roll <= 1) {
			System.out.println("No extra abilities.");
			tabAppend("No extra abilities.");
		} else if (roll == 2) {
			abilityList.add(staticAbilities.First_Strike);
			tabAppend("First Strike");
		} else if (roll == 3) {
			toughness += 3;
			abilityList.add(staticAbilities.Defender);
			tabAppend("+0/+3 (" + power + "/" + toughness + ")");
			tabAppend("Defender");
		} else if (roll == 4) {
			abilityList.add(staticAbilities.Flying);
			tabAppend("Flying");
		} else if (roll == 5) {
			// Add a random Pro
			staticAbilities[] tempArray = {
					staticAbilities.Protection_from_Red,
					staticAbilities.Protection_from_Blue,
					staticAbilities.Protection_from_Black,
					staticAbilities.Protection_from_White,
					staticAbilities.Protection_from_Green };
			int i = Engine.requestNumber();

			staticAbilities sa;
			if (i % 2 == 1) {
				sa = tempArray[((i - 1) / 2 + 1) - 1];
			} else {
				sa = tempArray[(i / 2) - 1];
			}

			abilityList.add(sa);
			tabAppend(sa.name().replace("_", " "));

		} else if (roll == 6) {
			abilityList.add(staticAbilities.Deathtouch);
			abilityList.add(staticAbilities.Defender);
			tabAppend("Deathtouch");
			tabAppend("Defender");
		} else if (roll == 7) {
			power += 2;
			toughness += 1;
			abilityList.add(staticAbilities.Flying);
			tabAppend("+2/+1");
			tabAppend("Flying");

		} else if (roll == 8) {
			power += 2;
			toughness += 2;
			abilityList.add(staticAbilities.Lifelink);
			tabAppend("+2/+2");
			tabAppend("Lifelink");
		} else if (roll == 9) {
			power += 3;
			abilityList.add(staticAbilities.Trample);
			abilityList.add(staticAbilities.Haste);
			tabAppend("+3/+0");
			tabAppend("Trample");
			tabAppend("Haste");

		} else if (roll == 10) {
			// Add a random Pro
			staticAbilities[] tempArray = {
					staticAbilities.Protection_from_Red,
					staticAbilities.Protection_from_Blue,
					staticAbilities.Protection_from_Black,
					staticAbilities.Protection_from_White,
					staticAbilities.Protection_from_Green };
			int i = Engine.requestNumber();

			staticAbilities sa;

			if (i % 2 == 1) {
				sa = tempArray[((i - 1) / 2 + 1) - 1];
			} else {
				sa = tempArray[i / 2 - 1];
			}

			abilityList.add(sa);
			abilityList.add(staticAbilities.Vigilance);
			abilityList.add(staticAbilities.Lifelink);

			tabAppend(sa.name().replace("_", " "));
			tabAppend("Vigilance");
			tabAppend("Lifelink");

		} else if (roll == 11) {
			power += 3;
			toughness += 3;
			abilityList.add(staticAbilities.Shroud);

			tabAppend("+3/+3");
			tabAppend("Shroud");

		} else if (roll == 12) {
			abilityList.add(staticAbilities.Annihilator_1);
			abilityList.add(staticAbilities.First_Strike);
			abilityList.add(staticAbilities.Flying);

			tabAppend("Annihilator 1");
			tabAppend("First Strike");
			tabAppend("Flying");
		} else if (roll == 13) {
			// Angel of Death
			abilityList.add(staticAbilities.Lifelink);
			abilityList.add(staticAbilities.Deathtouch);
			abilityList.add(staticAbilities.Flying);

			tabAppend("Lifelink");
			tabAppend("Deathtouch");
			tabAppend("Flying");
		} else if (roll == 14) {
			power += 4;
			abilityList.add(staticAbilities.Annihilator_2);
			tabAppend("+4/+0");
			tabAppend("Annihilator 2");
		} else if (roll == 15) {
			power += 2;
			toughness += 2;
			abilityList.add(staticAbilities.Lifelink);
			abilityList.add(staticAbilities.Flying);
			abilityList.add(staticAbilities.First_Strike);

			tabAppend("+2/+2");
			tabAppend("Lifelink");
			tabAppend("Flying");
			tabAppend("First Strike");

		} else if (roll >= 16) {
			// Welcome Progenitus to the world.
			abilityList.add(staticAbilities.Protection_from_Everything);
			tabAppend("Protection from Everything");
		}

		desc = desc.substring(0, desc.length() - 1);
	}

	// Other
	public Token(TYPE type, String describer) {
		tokenAmount++;
		consoleName = "TOK" + tokenAmount;
		this.type = type;
		desc = describer;

		tokenList.add(this);
		
		// Update Console List
		Console.updateTokens();
	}

	public static enum TYPE {
		Creature, Enchantment, Artifact;
	}

	public static enum staticAbilities {
		Lifelink, Deathtouch, Flying, First_Strike, Defender, Haste, Trample, Vigilance, Shroud,

		Protection_from_Black, Protection_from_Red, Protection_from_Blue, Protection_from_Green, Protection_from_White, Protection_from_Everything,

		Annihilator_1, Annihilator_2,
	}

	@Override
	public LinkedList<modifier> getMods() {
		LinkedList<modifier> list = new LinkedList<modifier>();
		list.add(modifier.INFO);
		list.add(modifier.DELETE);
		list.add(modifier.HP);
		list.add(modifier.TAP);
		list.add(modifier.UNTAP);
		return list;
	}

	@Override
	public String info() {
		String info = "\n";
		info += consoleName + " INFO:";
		info += "\n";
		info += "---------------";

		if (type.equals(TYPE.Creature)) {

			info = Console.tabAdd(info, ("P/T: " + power + "/" + toughness));
			info = Console.tabAdd(info, ("Type: Creature"));
			info = Console.tabAdd(info, ("Tapped: " + (tapped ? "Yes" : "No")));
			info = Console.tabAdd(info, "Static abilities: ");

			boolean hasAbilities = false;
			for (staticAbilities sa : abilityList) {
				hasAbilities = true;
				info += (sa.name().replace("_", " "));
				info += ", ";
			}

			if (hasAbilities) {
				info = info.substring(0, info.length() - 2);
			} else {
				info += "NONE.";
			}
		} else {
			// Enchantment info
		}

		return info;
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
		//CLEAN UP FOR EACH TOKEN
		if (desc.equals("All creatures tokens Deep IQ control get +1/+1.")){
			for (Token t: tokenList){
				t.power-=1;
				t.toughness-=1;
			}
		} 
		
		
		tokenList.remove(this);
		return ("Token \"" + consoleName + "\"" + " deleted ");
		// Updating is done in Console class, no need to update here.
	}

	@Override
	public String tap() {
		tapped = true;
		return(consoleName + " has been tapped.");
	}

	@Override
	public String untap() {
		tapped = false;
		return(consoleName + " has been untapped.");
	}

}
