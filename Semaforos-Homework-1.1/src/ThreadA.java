import java.util.concurrent.Semaphore;

public class ThreadA extends Thread {
	Semaphore semA, semB, semC;
	volatile boolean finalize = false;
	public ThreadA(Semaphore semA, Semaphore semB, Semaphore semC) {
		this.semA = semA;
		this.semB = semB;
		this.semC = semC;
	}
	@Override
	public void run() {
		while(!finalize) {
			try {
				semA.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("A");
			semB.release();
		}
	}
	public void endTask() {
		finalize = true;
	}
}
