
public class Passenger extends Thread{

	volatile boolean finalize = false;
	int id;
	Circuit circuit;
	
	public Passenger(int id, Circuit circuit) {
		this.id = id;
		this.setName("Passenger "+id);
		this.circuit = circuit;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			circuit.enterCircuit(this);
			System.out.println("Passenger enters circuit");
			circuit.getIn();
			System.out.println("Passenger gets into the shuttle");
			circuit.getOut();
			System.out.println("Passenger gets out of the shuttle");
			circuit.exitCircuit();
			System.out.println("Passenger exits the circuit");
		}
	}
	
	public void endTask() {
		this.finalize = finalize;
	}
	
}
