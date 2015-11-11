import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Circuit {

	final int MAX_PASSENGERS = 100;
	final int SHUTTLE_CAPACITY = 4;
	
	int passengersOnboard = 0;
	int place = 0;
	boolean shuttleInPlace = true;
	boolean readyToLoad = true;
	boolean readyToUnload = false;
	boolean readyToTravel = false;
	Lock lock = new ReentrantLock();
	Condition loadShuttleQueue = lock.newCondition();
	Condition unloadShuttleQueue = lock.newCondition();
	Condition shuttleLoaded = lock.newCondition();
	Condition shuttleUnloaded = lock.newCondition();
	
	
	MyAsynchronousMailbox<Passenger> leftSide = new MyAsynchronousMailbox<>(MAX_PASSENGERS);
	MyAsynchronousMailbox<Passenger> rightSide = new MyAsynchronousMailbox<>(MAX_PASSENGERS);
	
	MyAsynchronousMailbox<Passenger> shuttleMailbox = new MyAsynchronousMailbox<>(SHUTTLE_CAPACITY);

	
	public void getIn() {
		lock.lock();
		while(!readyToLoad || (passengersOnboard == SHUTTLE_CAPACITY)) {
			try {
				loadShuttleQueue.await();
			} catch (InterruptedException e) {}
		}
		shuttleMailbox.send(leftSide.receive());
		passengersOnboard++;
		if(passengersOnboard == SHUTTLE_CAPACITY) {
			readyToLoad = false;
			shuttleLoaded.signal();
		}
		lock.unlock();
	}
	
	public void load() {
		lock.lock();
		readyToTravel = false;
		readyToLoad = true;
		loadShuttleQueue.signalAll();
		while(passengersOnboard < SHUTTLE_CAPACITY) {
			try {
				shuttleLoaded.await();
			} catch (InterruptedException e) {}
		}
		readyToTravel = true;
		readyToLoad = false;
		lock.unlock();
	}
	
	public void travel() {
		lock.lock();
		if(place == 0) {
			place = 1;
		} else {
			place = 0;
		}
		lock.unlock();
	}
	
	public void unLoad() {
		lock.lock();
		readyToTravel = false;
		readyToUnload = true;
		unloadShuttleQueue.signalAll();
		while(passengersOnboard != 0) {
			try {
				shuttleUnloaded.await();
			} catch (InterruptedException e) {}
		}
		readyToTravel = true;
		readyToUnload = false;
		lock.unlock();
	}
	public void getOut() {
		lock.lock();
		while(!readyToUnload || (passengersOnboard == 0)) {
			try {
				unloadShuttleQueue.await();
			} catch (InterruptedException e) {}
		}
		rightSide.send( shuttleMailbox.receive() );
		passengersOnboard--;
		if(passengersOnboard == 0) {
			shuttleUnloaded.signal();
		}
		lock.unlock();
		
	}
	
	public void enterCircuit(Passenger p) {
		leftSide.send(p);
	}
	public void exitCircuit() {
		rightSide.receive();
	}
}

