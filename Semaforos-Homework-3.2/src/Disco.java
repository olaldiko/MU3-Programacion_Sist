import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Disco {

	final int MAX_PEOPLE = 20;
	
	int boyCount = 0;
	int girlCount = 0;
	
	Object boysLock = new Object();
	Object girlsLock = new Object();
	Object lock = new Object();
	Semaphore mutex = new Semaphore(1);
	Semaphore boys = new Semaphore(0);
	Semaphore capacity = new Semaphore(MAX_PEOPLE);
	Vector<Person> peopleInDisco = new Vector<>();
	
	public void enterDisco(Person p) {
		try{
		capacity.acquire();
		mutex.acquire();
			if(p instanceof Boy) {
					while(boyCount < girlCount) {
						try {
								mutex.release();
								System.out.println("Boy waits, more boys than girls in the disco");
								boys.acquire();
								mutex.acquire();
						} catch (InterruptedException e) {}
					}
			}
			if(p instanceof Boy) {
				boyCount++;
			} else {
				girlCount++;
			}
			System.out.println(p.toString()+" entered the disco - Boys: "+boyCount+" Girls: "+girlCount);
			peopleInDisco.addElement(p);
		}catch(Exception e) {}
			mutex.release();
	}
	
	public void exitDisco(Person p) {
		try {
			mutex.acquire();
			if(p instanceof Boy) {
				boyCount--;
			} else {
				girlCount--;
			}
			peopleInDisco.remove(p);
			if((boyCount < girlCount)) {
				boys.release(boys.getQueueLength());
			}
			capacity.release();
		} catch (InterruptedException e) {}
		mutex.release();
	}
}
