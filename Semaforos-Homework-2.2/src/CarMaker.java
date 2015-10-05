
public class CarMaker extends Thread {
	Parking p;
	volatile boolean finalize = false;
	int idCount = 0;
	
	final int waitTime = 500;
	
	public CarMaker(Parking p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		Car c;
		while(!finalize) {
			c = new Car(idCount, p);
			c.setName("Car "+idCount);
			c.start();
			idCount++;
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {}
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
