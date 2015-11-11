
public class Agent extends Thread {

	Table table;
	
	volatile boolean finalize = false;
	
	public Agent(Table table) {
		this.table = table;
	}
	
	@Override
	public void run() {
 		while(!finalize) {
 			try {
				table.putIngredients();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
 		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
