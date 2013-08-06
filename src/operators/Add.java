package operators;

public class Add implements Operator {

	@Override
	public int run(int a, int b) {
		return (a+b);
	}

	@Override
	public String getString() {
		return "+";
	}

}
