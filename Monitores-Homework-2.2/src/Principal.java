import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Principal {

	final int NUM_DWARFS = 7;
	final int NUM_SEATS = 4;
	DinningRoom dr = new DinningRoom(NUM_SEATS);
	Dwarf[] dwarfs  = new Dwarf[NUM_DWARFS];
	SnowWhite snowWhite = new SnowWhite(dr);

	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		IntStream.range(0, NUM_DWARFS).forEach(i -> {
			dwarfs[i] = new Dwarf(dr, i);
			dwarfs[i].setName("Dwarf "+i);
		});
	}
	
	public void startThreads() {
		Stream.of(dwarfs).forEach(Dwarf::start);
		snowWhite.start();
	}
	
	public void endThreads() {
		Stream.of(dwarfs).forEach(Dwarf::endTask);
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
