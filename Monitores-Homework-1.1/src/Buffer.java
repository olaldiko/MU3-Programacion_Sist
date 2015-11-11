import java.util.Vector;

public class Buffer {
	
	final int MAX_ITEMS = 10;
	
	Vector<Integer> buffer = new Vector<>();
	Object empty = new Object();
	Object full = new Object();
	Object mutex = new Object();
	volatile int items = 0;
	
	public int get() {
		int retVal = 0;
		synchronized(empty) {
			while(items == 0) {
				try {
					System.out.println("The buffer is empty, thread waits");
					empty.wait();
				} catch (InterruptedException e) {}
			}
		}
		synchronized(mutex) {
			retVal = buffer.remove(0);
			items--;
			synchronized (full) {
				full.notify();
			}
		}
		return retVal;
	}
	
	public void put(int n) {
		synchronized(full) {
			while(items == MAX_ITEMS) {
				try {
					System.out.println("The buffer is full, thread waits");
					full.wait();
				} catch (InterruptedException e) {}
			}
		}
		synchronized(mutex) {
			buffer.addElement(n);
			items++;
			synchronized (empty) {
				empty.notify();
			}
		}
	}
}
