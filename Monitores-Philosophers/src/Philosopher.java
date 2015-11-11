
public class Philosopher extends Thread {

	Table table;
	int id;
	
	volatile boolean finalize = false;
	
	public Philosopher(int id, Table table) {
		this.table = table;
		this.id = id;
		this.setName("Philosopher "+id);
	}
	
	@Override
	public void run() {
		while(!finalize) {
			try {
			table.eat(id);
			System.out.println(getName()+" is eating");
			Thread.sleep(100);
			table.think(id);
			System.out.println(getName()+" is thinking");
			Thread.sleep(200);
			} catch(InterruptedException e) {}
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
}
