import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Toilet {
	
	final int MAX_PEOPLE = 3;
	
	Vector<Person> peopleInToilet = new Vector<>();
	Vector<Person> waiters = new Vector<>();
	Semaphore lock = new Semaphore(1);
	Semaphore boySem = new Semaphore(0);
	Semaphore girlSem = new Semaphore(0);
	
	int cont = 0;
	int gender = 0;
	
	public void enterToilet(Person p) {
		try {
			lock.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			int currGender = p instanceof Boy ? 1 : 0;
			waiters.addElement(p);
			while((cont == MAX_PEOPLE) || (cont > 0 && currGender != gender)) {
				try {
					if(p instanceof Boy) {
						lock.release();
						boySem.acquire();
						System.out.println(p.toString()+"has waited");
						lock.acquire();
					} else {
						lock.release();
						girlSem.acquire();
						System.out.println(p.toString()+"has waited");
						lock.acquire();
					}
				
				} catch (InterruptedException e) {}
			}
			waiters.remove(p);
			cont++;
			gender = p instanceof Boy ? 1 : 0;
			peopleInToilet.addElement(p);
		lock.release();
	}
	
	
	public synchronized void exitToilet(Person p) {
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cont--;
		peopleInToilet.remove(p);
		if (cont == 0) {
			if(p instanceof Boy) {
				System.out.println("Last boy releases the lock");
				girlSem.release();
			} else {
				System.out.println("Last girl releases the lock");
				boySem.release();
			}
		}
		
		lock.release();

	}
}
