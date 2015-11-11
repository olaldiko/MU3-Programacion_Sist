import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Principal {

	final int CANNIBAL_QTY = 10;
	final int COOKER_QTY = 1;
	final int MISSIONARY_QTY = 10;
	Pot<Missionary> pot = new Pot<>(MISSIONARY_QTY);
	
	Cannibal[] cannibals = new Cannibal[CANNIBAL_QTY];
	Cooker[] cookers = new Cooker[COOKER_QTY];

	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		IntStream.range(0, CANNIBAL_QTY).forEach(i -> cannibals[i] = new Cannibal(i, pot));
		IntStream.range(0, COOKER_QTY).forEach(i -> cookers[i] = new Cooker(i, pot));
	}
	
	public void startThreads() {
		Stream.of(cannibals).forEach(Cannibal::start);
		Stream.of(cookers).forEach(Cooker::start);
	}
	
	public void endThreads() {
		Stream.of(cannibals).forEach(Cannibal::endTask);
		Stream.of(cookers).forEach(Cooker::endTask);
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
