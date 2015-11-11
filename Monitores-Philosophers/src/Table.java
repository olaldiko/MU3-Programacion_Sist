import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {

	int philosopherQty = 5;
	
	boolean[] forkAvailable = new boolean[philosopherQty];
	Lock mutex = new ReentrantLock();
	Condition forkQueue = mutex.newCondition();
	Semaphore maxEating = new Semaphore(philosopherQty-1);
	public Table(int philosopherQty) {
		this.philosopherQty = philosopherQty;
		
		for(int i = 0; i < philosopherQty; i++) {
			forkAvailable[i] = true;
		}
	}
	
	public void eat(int id) throws InterruptedException {
		maxEating.acquire();
		mutex.lock();
		if(id % 2 == 0) {
			while(!forkAvailable[id]) {
				forkQueue.await();
			}
			forkAvailable[id] = false;
			
			while(!forkAvailable[id == (philosopherQty-1) ? 0 : (id+1)]) {
				forkQueue.await();
			}
			forkAvailable[id == (philosopherQty-1) ? 0 : (id+1)] = false;
			
		} else {
			while(!forkAvailable[id == (philosopherQty-1) ? 0 : (id+1)]) {
				forkQueue.await();
			}
			forkAvailable[id == (philosopherQty-1) ? 0 : (id+1)] = false;
			
			while(!forkAvailable[id]) {
				forkQueue.await();
			}
			forkAvailable[id] = false;
			
		}
		mutex.unlock();
	}
	
	public void think(int id) throws InterruptedException {
		mutex.lock();
		forkAvailable[id] = true;
		forkAvailable[id == (philosopherQty-1) ? 0 : (id+1)] = true;
		forkQueue.signalAll();
		mutex.unlock();
		maxEating.release();
	}
}
