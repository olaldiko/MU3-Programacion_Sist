import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Principal {

	final int NUM_TAXIS = 10;
	final int NUM_PASSENGERS = 10;
	
	BoardingZone zone = new BoardingZone();
	Taxi[] taxis = new Taxi[NUM_TAXIS];
	Passenger[] passengers = new Passenger[NUM_PASSENGERS];
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		IntStream.range(0, NUM_TAXIS).forEach(i -> taxis[i] = new Taxi(zone, i));
		IntStream.range(0, NUM_PASSENGERS).forEach(i -> passengers[i] = new Passenger(zone, i));
	}
	public void startThreads() {
		Stream.of(taxis).forEach(Taxi::start);
		Stream.of(passengers).forEach(Passenger::start);
	}
	
	public void endThreads() {
		Stream.of(taxis).forEach(Taxi::endTask);
		Stream.of(passengers).forEach(Passenger::endTask);
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
