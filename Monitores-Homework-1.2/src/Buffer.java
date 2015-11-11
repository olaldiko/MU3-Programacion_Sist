import java.util.Vector;

public class Buffer {
	
	final int MAX_ITEMS = 10;
	
	Vector<Integer> buffer = new Vector<>();
	Object empty = new Object();
	Object full = new Object();
	Object mutex = new Object();
	Object valueReadedLock = new Object();
	Vector<Consumer> hasRead = new Vector<>();
	volatile int items = 0;
	int numThreads;
	int threadCount = 0;
	
	public Buffer(int numThreads) {
		this.numThreads = numThreads;
	}
	
	public int get(Consumer c) {
		int retVal = 0;
		synchronized(empty) {
			while(items == 0) {
				try {
					System.out.println("The buffer is empty, thread waits");
					empty.wait();
				} catch (InterruptedException e) {}
			}
		}
		synchronized(valueReadedLock) {
			while(hasRead.contains(c)) {
				try {
					System.out.println("Value already read, "+c.getName()+" waits");
					valueReadedLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		synchronized(mutex) {
			retVal = buffer.get(0);
		}
		synchronized (valueReadedLock) {
			if(threadCount < numThreads-1) {
				hasRead.addElement(c);
				threadCount++;
			} else {
				valueReadedLock.notifyAll();
				hasRead.clear();
				threadCount = 0;
				buffer.remove(0);
				items--;
				synchronized (full) {
					full.notify();
				}
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
