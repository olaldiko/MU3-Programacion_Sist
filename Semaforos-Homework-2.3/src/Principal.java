import java.util.Scanner;

public class Principal {

	Bridge bridge = new Bridge();
	CarMaker maker = new CarMaker(bridge);
	
	Scanner kb = new Scanner(System.in);
	
	public void startThreads(){
		maker.start();
	}
	
	public void stopThreads() {
		maker.endTask();
		try {
			maker.join();
		} catch (InterruptedException e) {}
	}
	
	public void exec() {
		startThreads();
		kb.nextLine();
		stopThreads();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
	
}
