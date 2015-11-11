import java.util.concurrent.BrokenBarrierException;

public class MonitorCyclicBarrier {

	int numThreads = 0;
	int count = 0;
	Runnable runnable = null;
	
	public MonitorCyclicBarrier(int numThreads) {
		this.numThreads = numThreads;
	}
	public MonitorCyclicBarrier(int numThreads, Runnable runnable) {
		this.numThreads = numThreads;
		this.runnable = runnable;
	}	
	
	public synchronized void await() throws InterruptedException, BrokenBarrierException{
		if(count < numThreads-1) {
			count++;
			this.wait();
		} else {
			if(runnable != null) {
				Thread tr = new Thread(runnable);
				tr.start();
			}
			count = 0;
			this.notifyAll();
		}
		
		
	}
	
}
