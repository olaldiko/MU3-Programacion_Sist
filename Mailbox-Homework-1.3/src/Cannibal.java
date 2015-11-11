
public class Cannibal extends Thread{

	volatile boolean finalize = false;
	Pot<Missionary> pot;
	int id;
	
	public Cannibal(int id, Pot<Missionary> pot) {
		this.id = id;
		this.setName("Cannibal "+id);
		this.pot = pot;
	}
	@Override
	public void run() {
		while(!finalize) {
			pot.receive();
			System.out.println(this.getName()+"eats a missionary");
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
