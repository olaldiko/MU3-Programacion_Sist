import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class BoardingZone {

	final int BOARD_ZONES = 3;
	
	MyAsynchronousMailbox<Taxi> taxiMailbox = new MyAsynchronousMailbox<>(10);
	MyAsynchronousMailbox<Passenger> passengerMailbox = new MyAsynchronousMailbox<>(10);
	Random r = new Random();
	
	Lock boardZoneLock = new ReentrantLock();
	
	Taxi[] boardZoneTaxis = new Taxi[BOARD_ZONES];
	Passenger[] boardZonePassengers = new Passenger[BOARD_ZONES];
	Condition[] boardZoneConditions = new Condition[BOARD_ZONES];
	Condition hasSpaceTaxis = boardZoneLock.newCondition();
	Condition hasSpacePassengers = boardZoneLock.newCondition();
	Passenger pass;
	
	public BoardingZone() {
		IntStream.range(0, BOARD_ZONES)
			.forEach(i -> boardZoneConditions[i] = boardZoneLock.newCondition());
	}
	
	public void enterZone(Taxi t) {
		taxiMailbox.send(t);
	}
	
	public void enterZone(Passenger p) {
		passengerMailbox.send(p);
	}
	
	public int enterBoardingZone(Passenger p) {
		boolean hasEntered = false;
		int zone = 0;
		boardZoneLock.lock();
		while(!hasEntered) {
			for(int i = 0; (i < BOARD_ZONES) && (!hasEntered); i++) {
				if(boardZonePassengers[i] == null) {
					boardZonePassengers[i] = p;
					hasEntered = true;
					zone = i;
				}
			}
			if(!hasEntered) {
				try {
					hasSpacePassengers.await();
				} catch (InterruptedException e) {}
			}
		}
		boardZoneConditions[zone].signalAll();
		boardZoneLock.unlock();
		return zone;
	}
	
	public int enterBoardingZone(Taxi t) {
		boolean hasEntered = false;
		int zone = 0;
		boardZoneLock.lock();
		while(!hasEntered) {
			for(int i = 0; (i < BOARD_ZONES) && (!hasEntered); i++) {
				if(boardZoneTaxis[i] == null) {
					boardZoneTaxis[i] = t;
					hasEntered = true;
					zone = i;
				}
			}
			if(!hasEntered) {
				try {
					hasSpaceTaxis.await();
				} catch (InterruptedException e) {}
			}
		}
		boardZoneConditions[zone].signalAll();
		boardZoneLock.unlock();
		return zone;
	}
	
	public void leaveZone(Passenger p) {
		boardZoneLock.lock();
		int zone = p.getBoardZone();
		while(boardZoneTaxis[zone] == null) {
			try {
				boardZoneConditions[zone].await();
			} catch (InterruptedException e) {}
		}
		boardZoneConditions[zone].signalAll();
		boardZoneLock.unlock();
		boardZoneTaxis[zone].enterTaxi(p);
		boardZoneLock.lock();
		boardZonePassengers[zone] = null;
		hasSpacePassengers.signalAll();
		boardZoneLock.unlock();
	}
	public void leaveZone(Taxi t) {
		boardZoneLock.lock();
		int zone = t.getBoardZone();
		while(boardZonePassengers[zone] == null) {
			try {
				boardZoneConditions[zone].await();
			} catch (InterruptedException e) {}
		}
		boardZoneLock.unlock();
		t.awaitPassenger();
		boardZoneLock.lock();
		boardZoneConditions[zone].signalAll();
		boardZoneTaxis[zone] = null;
		hasSpaceTaxis.signalAll();
	}
}
