package buffers;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import parts.Bucket;

public class BucketBuffer {

	final int MAX_PARTS = 2;
	volatile int numParts = 0;
	Vector<Bucket> buffer = new Vector<>();
	
	Semaphore put = new Semaphore(MAX_PARTS);
	Semaphore get = new Semaphore(0);
	Object lock = new Object();
	
	public void putBucket(Bucket b) {
		try {
			put.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (lock) {
			buffer.addElement(b);
			numParts++;
		}

		if(numParts == MAX_PARTS) {
			get.release(MAX_PARTS);
		}
		
	}
	
	public Bucket getBucket() {
		Bucket b;
		try {
			get.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (lock) {
			b = buffer.remove(0);
			numParts--;
		}

		if(numParts == 0) {
			put.release(MAX_PARTS);
		}
		return b;
	}
	
	public int getCapacity() {
		return MAX_PARTS;
	}
}
