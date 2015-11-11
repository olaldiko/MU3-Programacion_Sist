public class Shuttle extends Thread{

	
	final int CAPACITY = 4;
	volatile boolean finalize = false;
	int id;
	Circuit circuit;
	
	public Shuttle(int id, Circuit circuit) {
		this.id = id;
		this.setName("Shuttle "+id);
		this.circuit = circuit;
	}
	
	@Override
	public void run() {
		while (!finalize) {
			circuit.load();
			System.out.println("Shuttle loads passengers");
			circuit.travel();
			System.out.println("Shuttle travels");
			circuit.unLoad();
			System.out.println("Shuttle unloads passengers");
			circuit.travel();
			System.out.println("Shuttle travels");
		}
	}

	public void endTask() {
		this.finalize = true;
	}
	
}
