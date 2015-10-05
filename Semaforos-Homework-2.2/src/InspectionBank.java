import java.util.concurrent.Semaphore;

public class InspectionBank {
	int id;
	Semaphore inUse = new Semaphore(0);
	Semaphore free = new Semaphore(1);
	Car car;
	Parking parking;
	
	public InspectionBank(int id, Parking p) {
		this.id = id;
		this.parking = p;
	}
	
	public boolean putCar(Car c) {
		if (free.tryAcquire()) {
			this.car = c;
			System.out.println(c.toString()+" enters the inspection bank "+id);
			inUse.release();
			return true;
		} else {
			return false;
		}
	}
	
	public Car getCar() {
		Car retVal = null;
		try {
			inUse.acquire();
			retVal = this.car;
			free.release();
			parking.addAvaibleBank();
		} catch (InterruptedException e) {}
		return retVal;
	}
}
