import java.util.concurrent.Semaphore;

public class Car extends Thread{
	boolean checked = false;
	int i = 0;
	int id;
	Parking parking;
	
	Semaphore checkSemaphore = new Semaphore(0);
	public Car(int id, Parking parking) {
		this.id = id;
		this.parking = parking;
	}
	
	public void setChecked() {
		checked = true;
		checkSemaphore.release();
	}
	
	public void waitForCheck() {
		try {
			checkSemaphore.acquire();
		} catch (InterruptedException e) {}
	}
	
	@Override
	public void run() {
		if(parking.enterParking(this)) {
		System.out.println("The car "+id+" enters the parking");
		parking.goToInspection(this);
		System.out.println("The car "+id+" goes to the inspection area");
		waitForCheck();
		parking.exitParking(this);
		System.out.println("The car "+id+" passes inspection and goes home");
		} else {
			System.out.println("The car "+id+" has no place in the parking, so it goes home");
		}
	}
	public String toString() {
		return "Car ID: "+id;
	}
}
