import java.util.Scanner;

public class Principal {
	
	final int NUM_DWARFS = 7;
	DinningRoom dr = new DinningRoom();
	Dwarf[] dwarfs = new Dwarf[NUM_DWARFS];
	SnowWhite snowWhite;
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		for(int i = 0; i < NUM_DWARFS; i++) {
			dwarfs[i] = new Dwarf(dr);
			dwarfs[i].setName("Dwarf "+i);
		}
		snowWhite = new SnowWhite(dr);
		snowWhite.setName("Snow White");
	}
	
	public void startThreads() {
		for(Dwarf d : dwarfs) {
			d.start();
		}
		snowWhite.start();
	}
	
	public void endThreads() {
		
		for(Dwarf d : dwarfs) {
			d.endTask();
			try {
				d.join();
			} catch (InterruptedException e) {}
		}
		snowWhite.endTask();
		try {
		snowWhite.join();
		} catch (InterruptedException e) {}
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
