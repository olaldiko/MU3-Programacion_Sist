import java.util.Scanner;

public class Principal {

	HiloContador[] threads = new HiloContador[10];
	MonitorCyclicBarrier barrier = new MonitorCyclicBarrier(10);
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new HiloContador(i, 10, barrier);
			threads[i].setName("Thread "+i);
		}
	}
	
	public void startThreads() {
		for(HiloContador c : threads) {
			c.start();
		}
	}
	
	public void stopThreads() {
		for(HiloContador c : threads) {
			try {
				c.join();
			} catch (InterruptedException e) {}
		}
	}
	
	public void exec() {
		createThreads();
		startThreads();
		kb.nextLine();
		stopThreads();
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
		
	}
	

}
