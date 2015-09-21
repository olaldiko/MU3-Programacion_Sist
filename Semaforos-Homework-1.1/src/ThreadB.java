import java.util.concurrent.Semaphore;

public class ThreadB extends Thread {
	Semaphore semA, semB, semC;
	volatile boolean finalize = false;
	public ThreadB(Semaphore semA, Semaphore semB, Semaphore semC) {
		this.semA = semA;
		this.semB = semB;
		this.semC = semC;
	}
	@Override
	public void run() {
		while(!finalize) {
			try {
				semB.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("B");
			semC.release();
		}
	}
	public void endTask() {
		finalize = true;
	}
}
