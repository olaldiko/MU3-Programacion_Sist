import java.util.Random;
import java.util.Scanner;

public class Principal {

	final int NUM_THREADS = 100;
	
	Person[] people = new Person[NUM_THREADS];
	Disco d = new Disco();
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		Random r = new Random();
		for(int i = 0; i< people.length; i++) {
			people[i] = r.nextBoolean() ? new Boy(i, d) : new Girl(i, d);
			people[i].setName(people[i].toString());
		}
	}
	
	public void startThreads() {
		for(Person p : people) {
			p.start();
		}
	}
	
	public void stopThreads() {
		for(Person p : people) {
			p.endTask();
			try {
				p.join(1000);
				p.interrupt();
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
