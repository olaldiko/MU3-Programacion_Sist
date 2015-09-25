package buffers;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import parts.Part;

public class PartBuffer {
	
	final int MAX_PARTS = 1;
	
	Vector<Part> buffer = new Vector<>();
	
	Semaphore empty = new Semaphore(0);
	Semaphore full = new Semaphore(MAX_PARTS);
	
	Object lock = new Object();
	
	
	
	public void putPart(Part p) {
		try {
			full.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(lock) {
			buffer.addElement(p);
		}
		empty.release();
	}
	
	public Part getPart() {
		Part p;
		try {
			empty.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(lock) {
			p = buffer.remove(0);
		}
		full.release();
		return p;
	}
	
}
