import java.util.Vector;
import java.util.concurrent.Semaphore;

public class BarberShop {

	final int MAX_CLIENTS = 10;
	final int CUT_TIME = 0;
	Vector<Client> clients = new Vector<>();
	Barber barber = new Barber(0, this);
	int count = 0;
	Object dataLock = new Object();
	Semaphore capacity = new Semaphore(MAX_CLIENTS);
	Semaphore mutex = new Semaphore(1);
	Semaphore barberSem = new Semaphore(0);
	Semaphore clientSem = new Semaphore(1);
	public BarberShop() {
		barber.setName("Barber");
		barber.start();
	}
	
	
	public boolean enterBarberShop(Client c) {
		if(!capacity.tryAcquire()) {
			return false;
		} else {
			try {
				mutex.acquire();
				clients.addElement(c);
				count++;
				while(!c.isHairCut()) {
					barberSem.release();
					mutex.release();
					clientSem.acquire();
					System.out.println(c.toString()+" waits");
					mutex.acquire();
				}
				clients.remove(c);
				count--;
			} catch (InterruptedException e) {}
			mutex.release();
			return true;
		}
	}
	
	public void cutHair() {
		try{
			mutex.acquire();
			if(count > 0) {
				for(int i = 0; i < clients.size(); i++) {
						Thread.sleep(CUT_TIME);
						clients.get(i).setHairCut(true);
						System.out.println("Barber cuts the hair to "+clients.get(i).toString());
						mutex.release();
						clientSem.release();
						mutex.acquire();
				}
				mutex.release();
			} else {
				System.out.println("Barber waits");
				mutex.release();
				barberSem.acquire();
					
			}
		} catch (InterruptedException e) {}
	}
	
	public void closeShop() {
		barber.endTask();
		try {
			barber.join();
		} catch (InterruptedException e) {}
	}
}
