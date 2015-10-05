
public class Cannibal extends Thread {

	
	Pot pot;
	
	volatile boolean finalize = false;
	
	public Cannibal(Pot pot) {
		this.pot = pot;
	}
 	
	@Override
	public void run() {
		while(!finalize) {
			pot.getMissionary();
			System.out.println("The cannibal gets a missionary");
		}
		
	}
	public void endTask() {
		this.finalize = false;
	}
}
