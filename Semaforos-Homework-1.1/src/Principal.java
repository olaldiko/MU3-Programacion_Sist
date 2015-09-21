import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Principal {
	Semaphore semA = new Semaphore(1);
	Semaphore semB = new Semaphore(0);
	Semaphore semC = new Semaphore(0);
	ThreadA thA = new ThreadA(semA, semB, semC);
	ThreadB thB = new ThreadB(semA, semB, semC);
	ThreadC thC = new ThreadC(semA, semB, semC);
	Scanner kb = new Scanner(System.in);
	public void startThreads() {
		thA.start();
		thB.start();
		thC.start();
	}
	public void stopThreads() {
		thA.endTask();
		thB.endTask();
		thC.endTask();
		try {
			thA.join();
			thB.join();
			thC.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void exec() {
		startThreads();
		kb.nextLine();
		stopThreads();
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
