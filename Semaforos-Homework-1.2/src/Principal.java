import java.util.Scanner;

public class Principal {
	Buffer b = new Buffer();
	final int NUM_THREADS = 10;
	NumGeneratorThread[] threads = new NumGeneratorThread[NUM_THREADS];
	Scanner kb = new Scanner(System.in);
	boolean finalize = false;
	
	Thread viewer = new Thread(() -> {
		while(!finalize) {
			viewNumbers();
		}
	});
	
	public void createThreads() {
		for (int i = 0; i < NUM_THREADS; i++) {
			threads[i] = new NumGeneratorThread(b, i);
			threads[i].setName("NumGen " + i);
		}
	}
	
	public void runThreads() {
		for(int i = 0; i < NUM_THREADS; i++) {
			threads[i].start();
		}
		viewer.start();
	}
	
	public void stopThreads() {
		for(int i = 0; i < NUM_THREADS; i++) {
			threads[i].endTask();
			finalize = true;
			try {
			threads[i].join();
			viewer.join();
			} catch(Exception e) {};
		}

		
	}
	
	public void viewNumbers() {
		for(int i : b.getArray()) {
			System.out.print(i);
		}
		System.out.println();
	}
	
	public void exec() {
		createThreads();
		runThreads();
		kb.nextLine();
		stopThreads();

	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
