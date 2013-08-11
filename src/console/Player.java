package console;

import java.util.LinkedList;

import operators.Operator;

public class Player implements Noun {
	public int life = 20;
	public int stage = 1;

	private String tempName = "";
	public Type type;
	
	public Player(Type t) {
		type = t;
	}

	
	public enum Type {
		PLAYER,
		DIQ,
	}

	@Override
	public LinkedList<modifier> getMods() {
		LinkedList<modifier> list = new LinkedList<modifier>();
		list.add(modifier.INFO);
		list.add(modifier.HP);
		
		tempName = "Your";
		if (!(type.equals(Type.PLAYER))){
			list.add(modifier.STG);
			tempName = "Deep IQ's";
		}
		list.add(modifier.TURN);
		
		return list;
	}
	
	@Override
	public String info() {
		
		String info = "";
		
		if (type.equals(Type.PLAYER)){
			info += "PLAYER: INFO";
			info += "\n";
			info += "-------------";
			info += "\n";
			info += "Player type: Human Player";
			info += "\n";
			info += "Life: " + life;
			info += "\n";
			info += "-------------";
		} else {
			info += "DIQ: INFO";
			info += "\n";
			info += "-------------";
			info += "\n";
			info += "Player type: Deep IQ";
			info += "\n";
			info += "Life: " + life;
			info += "\n";
			info += "Stage: " + stage;
			info += "\n";
			info += "-------------";
		}
		
		return info;
	}

	@Override
	public String stage(Operator o, int b) {
		stage = o.run(stage, b);
		return (tempName + " stage has been set to " + stage + ".");
	}

	@Override
	public String hp(Operator o, int b) {
		life = o.run(life, b);
		return (tempName + " life total has been set to " + life + ".");
	}

	
	
	
	//HARD TO DO. MUST UNDO TURNS (CREATE SAVE STATE FOR EACH TURN).
	@Override
	public String turn(Operator o, int b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		//NOT SUPPORTED
		return null;
	}

	@Override
	public String tap() {
		//NOT SUPPORTED
		return null;
	}

	@Override
	public String untap() {
		//NOT SUPPORTED
		return null;
	}
	

}
