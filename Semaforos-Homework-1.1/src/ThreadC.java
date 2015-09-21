import java.util.concurrent.Semaphore;

public class ThreadC extends Thread {
	Semaphore semA, semB, semC;
	volatile boolean finalize = false;
	public ThreadC(Semaphore semA, Semaphore semB, Semaphore semC) {
		this.semA = semA;
		this.semB = semB;
		this.semC = semC;
	}
	@Override
	public void run() {
		while(!finalize) {
			try {
				semC.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("C");
			semA.release();
		}
	}
	public void endTask() {
		finalize = true;
	}
}
