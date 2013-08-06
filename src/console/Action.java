package console;

public class Action {

	
	//placeholder values
	public static final int[] DO_NOTHING_VALUES = {7,6,5,4,3,2}; 
	
	
	public static void actionLookup(int stage, int roll){
		if (roll<=DO_NOTHING_VALUES[stage-1]){
			System.out.println("Deep IQ did nothing!");
			return;
		}
			
		if (stage == 1){
			
		} else if (stage == 2){
			
		} else if (stage == 3){
			
		} else if (stage == 4){
			
		} else if (stage == 5){
			
		} else if (stage == 6){
			
		} else {
			System.out.println("WTF");
		}
	}
	
}
