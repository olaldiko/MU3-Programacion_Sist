
public class Cooker extends Thread{

	volatile boolean finalize = false;
	int id;
	Pot<Missionary> pot;
	public Cooker(int id, Pot<Missionary> pot) {
		this.id = id;
		this.setName("Cooker "+id);
		this.pot = pot;
	}
	@Override
	public void run() {
		while(!finalize) {
			pot.send(new Missionary());
			System.out.println("Cooker puts a missionary in the pot");
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
