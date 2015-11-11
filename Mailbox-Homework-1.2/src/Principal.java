import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Principal {

	final int INSPECTORS_QTY = 3;
	final int CAR_QTY = 30;
	
	Parking parking = new Parking();
	Inspector[] inspectors = new Inspector[INSPECTORS_QTY];
	Car[] cars = new Car[CAR_QTY];
	
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		IntStream.range(0, INSPECTORS_QTY).forEach(i -> inspectors[i] = new Inspector(i, parking));
		IntStream.range(0, CAR_QTY).forEach(i -> cars[i] = new Car(i, parking));
	}
	
	public void startThreads() {
		Stream.of(inspectors).forEach(Inspector::start);
		Stream.of(cars).forEach(Car::start);
	}
	
	public void stopThreads() {
		Stream.of(inspectors).forEach(Inspector::endTask);
		Stream.of(cars).forEach(Car::endTask);
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
