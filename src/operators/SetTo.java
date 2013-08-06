package operators;

public class SetTo implements Operator{ 
	
	@Override
	public int run(int a, int b) {
		return b;
	}

	@Override
	public String getString() {
		return "=";
	}
}
