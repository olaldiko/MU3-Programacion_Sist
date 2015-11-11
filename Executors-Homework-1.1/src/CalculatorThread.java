import java.util.concurrent.Callable;

public class CalculatorThread implements Callable<Calculation> {

	Calculation c;
	
	
	public CalculatorThread(Calculation c) {
		this.c = c;
	}
	@Override
	public Calculation call() throws Exception {
		double operation = c.getValues().remove(0);
		switch(c.getOperation()) {
			case ADD:
				for(Integer i : c.getValues()) {
					operation += i;
				}
				break;
			case REMOVE:
				for(Integer i : c.getValues()) {
					operation -= i;
				}
				break;
			case MULTIPLY:
				for(Integer i : c.getValues()) {
					operation *= i;
				}
				break;
			case DIVIDE:
				for(Integer i : c.getValues()) {
					operation /= i;
				}
				break;
			case MOD:
				for(Integer i : c.getValues()) {
					operation %= i;
				}
				break;
		}
		c.setResult((int) operation);
		return c;
	}
	
	
}
