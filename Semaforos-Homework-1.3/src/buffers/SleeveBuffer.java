package buffers;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import parts.Sleeve;

public class SleeveBuffer {
	final int MAX_PIECES = 10;
	
	Vector<Sleeve> buffer = new Vector<>();
	
	Semaphore full = new Semaphore(MAX_PIECES);
	Semaphore empty = new Semaphore(0);
	
	Object lock = new Object();
	
	public void putSleeve(Sleeve s) {
		try {
			full.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(lock) {
			buffer.addElement(s);
		}
		empty.release();
	}
	
	public Sleeve getSleeve() {
		Sleeve s = null;
		try {
			empty.acquire();
			synchronized(lock) {
				s = buffer.remove(0);
			}
			full.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
