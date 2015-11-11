import java.util.Random;
import java.util.Scanner;

public class Principal {
	
	final int NUM_THREADS = 10;
	
	Person[] people = new Person[NUM_THREADS];
	Toilet t = new Toilet();
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		Random r = new Random();
		for(int i = 0; i< people.length; i++) {
			people[i] = r.nextInt(2) == 0 ? new Boy(i, t) : new Girl(i, t);
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
				p.join();
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
