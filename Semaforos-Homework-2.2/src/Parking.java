import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Parking {

	final int MAX_CAPACITY = 10;
	final int INSPECTION_BANK_NO = 3;
	Vector<Car> buffer = new Vector<>();
	
	
	Semaphore empty = new Semaphore(0);
	Semaphore full = new Semaphore(MAX_CAPACITY);
	
	Object lock = new Object();
	
	Semaphore inspectionSem = new Semaphore(INSPECTION_BANK_NO);
	
	InspectionBank[] banks = new InspectionBank[INSPECTION_BANK_NO];
	
	Inspector[] inspectors = new Inspector[INSPECTION_BANK_NO];
	
	
	volatile boolean terminateInspections = false;
	
	public Parking() {
		initializeBanks();
		initializeInspectors();
	}
	
	public void startInspections() {
		startInspectors();
	}
	
	public void stopInspections() {
		terminateInspections = true;
		while(!(empty.availablePermits() == 0));
		for(int i = 0 ; i < inspectors.length; i++) {
			inspectors[i].endTask();
			try {
				inspectors[i].join();
			} catch (InterruptedException e) {}
		}
	}
	
	public void initializeBanks() {
		for(int i = 0 ; i < banks.length; i++ ) {
			banks[i] = new InspectionBank(i, this);
		}
	}
	public void initializeInspectors() {
		for(int i = 0; i < inspectors.length; i++) {
			inspectors[i] = new Inspector(i, banks[i]);
			inspectors[i].setName("Inspector "+i);
		}
	}
	public void startInspectors() {
		for(int i = 0; i < inspectors.length; i++) {
			inspectors[i].start();
		}
	}
	
	public void stopInspectors() {
		for(int i = 0 ; i < inspectors.length; i++) {
			inspectors[i].endTask();
		}
	}
	
	public boolean enterParking(Car c) {
		
		if(full.tryAcquire()) {
			buffer.addElement(c);
			empty.release();
			return true;
		}else {
			return false;
		}
	}
	
	public void goToInspection(Car c) {
		try {
			inspectionSem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CheckAvailableInspector(c);	
	}
	
	public void addAvaibleBank() {
		inspectionSem.release();
	}
	
	public void CheckAvailableInspector(Car c) {
		synchronized (lock) {
			for(int i = 0 ; i < INSPECTION_BANK_NO; i++) {
				if(banks[i].putCar(c)) {
					break;
				}
			}
		}
	}

	public void exitParking(Car car) {
		try {
			empty.acquire();
			buffer.remove(car);
		} catch (InterruptedException e) {}
		if(!terminateInspections) {
			full.release();
		}
	}
	
}
