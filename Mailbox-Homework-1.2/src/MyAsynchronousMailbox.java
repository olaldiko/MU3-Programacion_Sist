import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyAsynchronousMailbox <T> {
	Vector<T> mailbox = new Vector<>();
	volatile int numElements = 0;
	int maxElements;
	Lock lock = new ReentrantLock();
	Condition empty = lock.newCondition();
	Condition full = lock.newCondition();
	
	public MyAsynchronousMailbox(int maxElements) {
		this.maxElements = maxElements;
	}
	
	public void send(T element) {
		lock.lock();
		while(numElements == maxElements) {
			try {
				full.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mailbox.addElement(element);
		numElements++;
		empty.signal();
		lock.unlock();
	}
	
	public boolean trySend(T element) {
		boolean hasSended = false;
		lock.lock();
		if(numElements < maxElements) {
			mailbox.addElement(element);
			numElements++;
			empty.signal();
			hasSended = true;
		}
		lock.unlock();
		return hasSended;
	}
	
	public T receive() {
		T retVal;
		lock.lock();
		while(numElements == 0) {
			try {
				empty.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		retVal = mailbox.remove(0);
		numElements--;
		full.signal();
		lock.unlock();
		return retVal;
	}
	
	public int getSize() {
		int retVal;
		lock.lock();
		retVal = mailbox.size();
		lock.unlock();
		return retVal;
	}
}
