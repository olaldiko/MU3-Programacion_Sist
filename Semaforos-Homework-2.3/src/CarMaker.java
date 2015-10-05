import java.util.Random;

public class CarMaker extends Thread {

	Bridge bridge;
	
	int carId = 0;
	
	volatile boolean finalize = false;
	
	Random rand = new Random();
	public CarMaker(Bridge bridge) {
		this.setName("Car Making Thread");
		this.bridge = bridge;
		
	}
	
	@Override
	public void run() {
		Car car;
		Direction d;
		while(!finalize) {
			switch(rand.nextInt(2)) {
				case 0: d = Direction.LEFT; break;
				case 1: d = Direction.RIGHT; break;
				default: d = Direction.LEFT;
			}
			car = new Car(carId, d, bridge);
			car.setName("Car ID: "+carId);
			car.start();
			carId++;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
