import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Parking {

	int maxElements = 20;
	int numBanks = 3;
	int carsInBanks = 0;
	Lock lock = new ReentrantLock();
	Condition bankAvailable = lock.newCondition();
	MyAsynchronousMailbox<Car> cars = new MyAsynchronousMailbox<>(maxElements);
	Vector<MyAsynchronousMailbox<Car>> inspectionBanks = new Vector<>();
	
	public Parking() {
		IntStream.range(0, numBanks).forEach(i -> inspectionBanks.addElement(new MyAsynchronousMailbox<Car>(1)));
	}
	
	
	public boolean enterParking(Car c) {
		return cars.trySend(c);
	}
	
	public void enterInspectionBank() {
		boolean hasEntered = false;
		Car c;
		lock.lock();
		while(carsInBanks == numBanks) {
			try {
				bankAvailable.await();
			} catch (InterruptedException e) {}
		}
		c = cars.receive();
		for(int i = 0; i < numBanks && !hasEntered; i++) {
			hasEntered = inspectionBanks.get(i).trySend(c);
		}
		carsInBanks++;
		lock.unlock();
	}
	public Car getCarForInspection(Inspector insp) {
		Car c;
		c = inspectionBanks.get(insp.getInspectorId()).receive();
		lock.lock();
		carsInBanks--;
		bankAvailable.signal();
		lock.unlock();		
		return c;
	}
	
}
