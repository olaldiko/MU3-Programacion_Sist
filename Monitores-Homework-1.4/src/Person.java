
public abstract class Person extends Thread{

	final int WAIT_TIME = 0;
	
	Disco disco;
	int id;
	volatile boolean finalize = false;
	
	
	public Person(int id, Disco disco) {
		this.id = id;
		this.disco = disco;
	}
	
	
	@Override
	public void run() {
		while(!finalize) {
			disco.enterDisco(this);
			System.out.println(toString()+" enters the disco");
			disco.dance(this);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(toString()+" dances");
			disco.exitDisco(this);
			System.out.println(toString()+" exits the disco");
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {}
		}
		
		
	}
	
	public void endTask() {
		this.finalize = true;
	}
	public String toString() {
		return "Person "+id;
	}
}
