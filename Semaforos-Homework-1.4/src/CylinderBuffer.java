import java.util.Vector;
import java.util.concurrent.Semaphore;

public class CylinderBuffer {

	final int MAX_PARTS = 3;
	volatile int numParts = 0;
	
	volatile Vector<Cylinder> buffer = new Vector<>();
	
	Semaphore put = new Semaphore(MAX_PARTS);
	Semaphore get = new Semaphore(0);
	Object lock = new Object();
	
	public void putCylinder(Cylinder c) {
		try {
			put.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(lock) {
			buffer.addElement(c);
			numParts++;
			if(numParts == MAX_PARTS) {
				get.release(MAX_PARTS);
			}
		}
	}
	public Cylinder getCylinder() {
		Cylinder c;
		try {
			get.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(lock) {
			c = buffer.remove(0);
			numParts--;
			if(numParts == 0) {
				put.release(MAX_PARTS);
			}
		}
		return c;
	}
	public int getCapacity() {
		return MAX_PARTS;
	}
}
