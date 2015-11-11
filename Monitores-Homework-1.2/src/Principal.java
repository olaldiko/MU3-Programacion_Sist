import java.util.Scanner;

public class Principal {

	final int CONS_NUM = 3;
	
	Buffer buffer = new Buffer(CONS_NUM);
	Producer producer;
	Consumer[] consumers = new Consumer[CONS_NUM];

	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		for (int i = 0; i < consumers.length; i++) {
			consumers[i] = new Consumer(buffer);
			consumers[i].setName("Consumer "+i);
		}
		producer = new Producer(buffer);
		producer.setName("Producer");
	}
	
	public void startThreads() {
		producer.start();
		for(Consumer c : consumers) {
			c.start();
		}
	}
	
	public void stopThreads() {
		for(Consumer c : consumers) {
			c.endTask();
			try {
				c.join();
			} catch (InterruptedException e) {}
		}
		producer.endTask();
		try {
			producer.join();
		} catch (InterruptedException e) {}
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
