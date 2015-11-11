import java.util.Vector;

public class Disco {

	final int MAX_PEOPLE = 20;
	
	int boyCount = 0;
	int girlCount = 0;
	
	Vector<Boy> boysQueue = new Vector<>();
	Vector<Girl> girlsQueue = new Vector<>();
	Vector<Person> peopleInDisco = new Vector<>();
	Object boysLock = new Object();
	Object girlsLock = new Object();
	Object danceFloorLock = new Object();
	Object capacityLock = new Object();
	Object mutex = new Object();
	
	public void enterDisco(Person p) {
		synchronized (capacityLock) {
			while ((boyCount + girlCount) == MAX_PEOPLE) {
				try {
					capacityLock.wait();
				} catch (InterruptedException e) {}
			}
		}
		synchronized (mutex) {
			if (p instanceof Boy) {
				boysQueue.add((Boy) p);
				peopleInDisco.addElement(p);
				boyCount++;
				synchronized (girlsLock) {
					girlsLock.notify();
				}
			} else {
				girlsQueue.add((Girl) p);
				peopleInDisco.addElement(p);
				girlCount++;
				synchronized (boysLock) {
					boysLock.notify();
				}
			}
		}	
	}
	
	public void dance(Person p) {
		if(p instanceof Boy) {
			synchronized (boysLock) {
				while (girlsQueue.isEmpty()) {
					try {
						boysLock.wait();
					} catch (InterruptedException e) {}
				}
				synchronized (mutex) {
					girlsQueue.remove(0);
				}
			}
		} else {
			synchronized (girlsLock) {
				while (boysQueue.isEmpty()) {
					try {
						girlsLock.wait();
					} catch (InterruptedException e) {}
				}
				synchronized (mutex) {
					boysQueue.remove(0);
				}
			}
		}
	}
	
	public void exitDisco(Person p) {
		synchronized (mutex) {
			if(p instanceof Boy) {
				boyCount--;
			} else {
				girlCount--;
			}
			peopleInDisco.remove(p);
			synchronized (capacityLock) {
				capacityLock.notify();
			}
		}
	}
}
