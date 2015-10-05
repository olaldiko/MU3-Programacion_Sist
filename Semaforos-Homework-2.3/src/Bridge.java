import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Bridge {

	final int MAX_CARS = 1;
	
	Vector<Car> cars = new Vector<>();

	Semaphore rightCrossing = new Semaphore(0);
	Semaphore leftCrossing = new Semaphore(0);
	Semaphore carsInBridge = new Semaphore(MAX_CARS);
	
	public void enterBridge(Car c) {
			if(c.getDirection() == Direction.LEFT && carsInBridge.availablePermits() == MAX_CARS) {
				rightCrossing.release(MAX_CARS-1);
			} else if (c.getDirection() == Direction.RIGHT && carsInBridge.availablePermits() == MAX_CARS) {
				leftCrossing.release(MAX_CARS-1);
			}
		try {
			if(c.getDirection() == Direction.LEFT && carsInBridge.availablePermits() < MAX_CARS) {
				rightCrossing.acquire();
			} else if (c.getDirection() == Direction.RIGHT && carsInBridge.availablePermits() < MAX_CARS) {
				leftCrossing.acquire();
			}
	
				carsInBridge.acquire();
		} catch (InterruptedException e) {}
		cars.addElement(c);
	}
	
	public void exitBridge(Car c) {
		carsInBridge.release();
		if(c.getDirection() == Direction.LEFT && carsInBridge.availablePermits() < MAX_CARS) {
			leftCrossing.release();
		} else if (c.getDirection() == Direction.RIGHT && carsInBridge.availablePermits() < MAX_CARS){
			rightCrossing.release();
		}
		if(c.getDirection() == Direction.LEFT && carsInBridge.availablePermits() == (MAX_CARS)) {
			rightCrossing.release(MAX_CARS);
		}else if (c.getDirection() == Direction.RIGHT && carsInBridge.availablePermits() == (MAX_CARS)) {
			leftCrossing.release(MAX_CARS);
		}
		cars.remove(c);
	}
	
}
