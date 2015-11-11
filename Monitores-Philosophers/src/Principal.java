import java.util.Scanner;
import java.util.stream.Stream;

public class Principal {

	final int PHILOSOPHER_QTY = 5;
	
	Table table = new Table(PHILOSOPHER_QTY);
	Philosopher[] philosophers = new Philosopher[PHILOSOPHER_QTY];
	
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		for(int i = 0; i < PHILOSOPHER_QTY; i++) {
			philosophers[i] = new Philosopher(i, table);
		}
	}
	
	public void startThreads() {
		Stream.of(philosophers)
			.forEach(Philosopher::start);
	}
	
	public void stopThreads() {
		Stream.of(philosophers)
			.forEach(Philosopher::endTask);
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
