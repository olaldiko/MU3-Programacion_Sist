
public class Principal {

	CalculatorPool calc = new CalculatorPool();
	
	public void exec() {
		calc.loadCalculations();
		calc.calculate();
		calc.printResults();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}

}
