import java.util.Scanner;

public class Principal {
	
	final int NUM_THREADS = 15;
	BarberShop barberShop = new BarberShop();
	Client[] clients = new Client[NUM_THREADS];
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		for(int i = 0; i < NUM_THREADS; i++) {
			clients[i] = new Client(i, barberShop);
			clients[i].setName(clients[i].toString());
		}
	}
	
	public void startThreads() {
		for(Client c : clients) {
			c.start();
		}
	}
	
	public void stopThreads() {
		for(Client c : clients) {
			c.endTask();
			try {
			c.join();
			} catch (InterruptedException e) {}
		}
		barberShop.closeShop();
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
