import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car extends Thread{

	int id;
	Parking parking;
	boolean isInspected = false;
	volatile boolean finalize = false;
	
	Lock lock = new ReentrantLock();
	Condition inspectedCond = lock.newCondition();
	
	public Car(int id, Parking parking) {
		this.id = id;
		this.setName("Car "+id);
		this.parking = parking;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			if(parking.enterParking(this)) {
				System.out.println(this.getName()+" enters parking");
				parking.enterInspectionBank();
				System.out.println(this.getName()+" enters inspection bank");
				waitForInspection();
				System.out.println(this.getName()+" passes inspection and leaves");
			} else {
				System.out.println("No place in parking, "+this.getName()+" leaves");
			}
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
	public void waitForInspection() {
		lock.lock();
		while(!isInspected) {
			try {
				inspectedCond.await();
			} catch (InterruptedException e) {}
		}
		lock.unlock();
	}
	public void passInspection() {
		lock.lock();
		this.isInspected = true;
		inspectedCond.signal();
		lock.unlock();
	}
}
