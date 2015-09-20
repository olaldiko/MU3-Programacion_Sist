import java.util.Scanner;
import java.util.Vector;

public class Principal {
	final int NUMS_PER_THREAD = 1000;
	final int NUM_THREADS = 100;
	Vector<Integer> list = new Vector<>();
	PrimeFinder[] threads = new PrimeFinder[NUM_THREADS];
	Scanner teclado = new Scanner(System.in);
	public void startThreads() {
		for(int i = 0; i < NUM_THREADS; i++) {
			threads[i] = new PrimeFinder((i* NUMS_PER_THREAD), NUMS_PER_THREAD, list);
			threads[i].setName("PrimeFinder from " + (i * NUMS_PER_THREAD) + " to " + ((i * NUMS_PER_THREAD)+NUMS_PER_THREAD));
			threads[i].start();
		}
	}
	public void waitThreads() {
		for(int i = 0; i < NUM_THREADS; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void printList() {
		for(Integer i : list) {
			System.out.println("Prime found: " + i);
		}
	}
	public void exec() {
		System.out.println("Press enter to start:");
		teclado.nextLine();
		startThreads();
		waitThreads();
		printList();
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
