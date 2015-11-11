import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Principal {

	final int PASSENGER_QTY = 5;
	
	Circuit circuit = new Circuit();
	Shuttle shuttle = new Shuttle(1, circuit);
	Passenger[] passengers = new Passenger[PASSENGER_QTY];
	
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		IntStream.range(0 , PASSENGER_QTY).forEach(i -> passengers[i] = new Passenger(i, circuit));
	}
	
	public void startThreads() {
		Stream.of(passengers).forEach(Passenger::start);
		shuttle.start();
	}
	
	public void endThreads() {
		Stream.of(passengers).forEach(Passenger::endTask);
		shuttle.endTask();
	}
	
	public void exec() {
		createThreads();
		startThreads();
		kb.nextLine();
		endThreads();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
