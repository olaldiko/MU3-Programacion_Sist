import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Taxi extends Thread {

	volatile boolean finalize = false;
	int id;
	int boardZone;
	BoardingZone zone;
	Passenger p;
	boolean isEmpty = true;
	Lock lock = new ReentrantLock();
	Condition hasSpace = lock.newCondition();
	Condition passengerAboard = lock.newCondition();
	public Taxi(BoardingZone zone, int id) {
		this.zone = zone;
		this.id = id;
		this.setName("Taxi "+id);
	}
	
	@Override
	public void run() {
		while (!finalize) {
			zone.enterZone(this);
			boardZone = zone.enterBoardingZone(this);
			zone.leaveZone(this);
		}
	}
	
	public void awaitPassenger() {
		lock.lock();
		while(isEmpty) {
			try {
				passengerAboard.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(p.getName()+" leaves in "+this.getName());
		p = null;
		isEmpty = true;
		hasSpace.signalAll();
		lock.unlock();
	}
	
	public void enterTaxi(Passenger p) {
		lock.lock();
		while(!isEmpty) {
			try {
				hasSpace.await();
			} catch (InterruptedException e) {}
		}
		System.out.println(p.getName()+" has entered "+this.getName());
		this.p = p;
		isEmpty = false;
		passengerAboard.signal();
		lock.unlock();
	}
	
	public int getBoardZone() {
		return this.boardZone;
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
